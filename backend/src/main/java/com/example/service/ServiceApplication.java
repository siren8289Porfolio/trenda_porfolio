package com.example.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ServiceApplication {

    public static void main(String[] args) {
        String databaseUrl = System.getenv("DATABASE_URL");
        String springUrl = System.getenv("SPRING_DATASOURCE_URL");
        String springUser = System.getenv("SPRING_DATASOURCE_USERNAME");
        String springPass = System.getenv("SPRING_DATASOURCE_PASSWORD");

        if (databaseUrl != null && !databaseUrl.isBlank() && databaseUrl.startsWith("postgresql://")) {
            try {
                ParsedDbUrl parsed = parsePostgresUrl(databaseUrl);
                setDatasourceProperties(parsed.jdbcUrl, parsed.username, parsed.password);
                System.out.println(">>> [ServiceApplication] Using DATABASE_URL -> " + parsed.host + ":" + parsed.port + "/" + parsed.dbName);
            } catch (Exception e) {
                System.err.println(">>> [ServiceApplication] DATABASE_URL parse failed: " + e.getMessage());
                fallbackToLocalOrFail(springUrl, springUser, springPass);
            }
        } else if (springUrl != null && !springUrl.isBlank()) {
            setDatasourceProperties(springUrl, springUser != null ? springUser : "", springPass != null ? springPass : "");
            System.out.println(">>> [ServiceApplication] Using SPRING_DATASOURCE_URL");
        } else {
            fallbackToLocalOrFail(springUrl, springUser, springPass);
        }
        SpringApplication.run(ServiceApplication.class, args);
    }

    /**
     * postgresql://user:password@host:port/dbname?query 를 수동 파싱.
     * java.net.URI 사용 안 함 (http:// 치환 시 query/특수문자에서 예외 발생).
     */
    private static ParsedDbUrl parsePostgresUrl(String url) {
        String rest = url.substring("postgresql://".length());
        int at = rest.lastIndexOf('@');
        if (at <= 0) {
            throw new IllegalArgumentException("Invalid postgres URL: no @user:password");
        }
        String userInfo = rest.substring(0, at);
        String hostPath = rest.substring(at + 1);
        int firstColon = userInfo.indexOf(':');
        String username = firstColon > 0 ? userInfo.substring(0, firstColon) : userInfo;
        String password = firstColon > 0 ? userInfo.substring(firstColon + 1) : "";

        int slash = hostPath.indexOf('/');
        String hostPort = slash > 0 ? hostPath.substring(0, slash) : hostPath;
        String pathAndQuery = slash > 0 ? hostPath.substring(slash + 1) : "";
        int q = pathAndQuery.indexOf('?');
        String dbName = q >= 0 ? pathAndQuery.substring(0, q) : pathAndQuery;
        String query = q >= 0 ? pathAndQuery.substring(q + 1) : "";

        int colon = hostPort.lastIndexOf(':');
        String host = colon > 0 ? hostPort.substring(0, colon) : hostPort;
        int port = 5432;
        if (colon > 0 && colon + 1 < hostPort.length()) {
            try {
                port = Integer.parseInt(hostPort.substring(colon + 1));
            } catch (NumberFormatException ignored) {
            }
        }

        String jdbcUrl = "jdbc:postgresql://" + host + ":" + port + "/" + dbName;
        if (!query.isEmpty()) {
            jdbcUrl += "?" + query;
        }
        return new ParsedDbUrl(jdbcUrl, username, password, host, port, dbName);
    }

    private static final class ParsedDbUrl {
        final String jdbcUrl;
        final String username;
        final String password;
        final String host;
        final int port;
        final String dbName;

        ParsedDbUrl(String jdbcUrl, String username, String password, String host, int port, String dbName) {
            this.jdbcUrl = jdbcUrl;
            this.username = username;
            this.password = password;
            this.host = host;
            this.port = port;
            this.dbName = dbName;
        }
    }

    private static void fallbackToLocalOrFail(String springUrl, String springUser, String springPass) {
        if (System.getenv("PORT") != null && !System.getenv("PORT").isEmpty()) {
            System.err.println(">>> [ServiceApplication] PORT set but DATABASE_URL/SPRING_DATASOURCE_URL missing. Set DATABASE_URL in Render Environment.");
            throw new IllegalStateException("DATABASE_URL or SPRING_DATASOURCE_URL required when PORT is set. Add DATABASE_URL in Render → Environment.");
        }
        setDatasourceProperties(
            "jdbc:postgresql://localhost:5432/trenda",
            "trenda",
            "trenda_password"
        );
        System.out.println(">>> [ServiceApplication] Using local default (localhost:5432/trenda)");
    }

    private static void setDatasourceProperties(String url, String username, String password) {
        System.setProperty("spring.datasource.url", url);
        System.setProperty("spring.datasource.username", username);
        System.setProperty("spring.datasource.password", password);
        System.setProperty("spring.datasource.driver-class-name", "org.postgresql.Driver");
        System.setProperty("spring.jpa.database-platform", "org.hibernate.dialect.PostgreSQLDialect");
    }
}
