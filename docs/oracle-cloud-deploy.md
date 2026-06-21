# Oracle Cloud Docker 배포

이 설정은 Oracle Cloud VM 또는 Oracle Cloud Container Registry에 Docker 이미지로 배포하는 흐름을 기준으로 합니다.

## 로컬 빌드 확인

```bash
docker compose -f docker-compose.oracle.yml --env-file env.oracle.sample build
```

## Oracle Cloud VM 실행

VM에 Docker와 Docker Compose 플러그인을 설치한 뒤, 실제 값으로 `.env`를 만들고 실행합니다.

```bash
cp env.oracle.sample .env
docker compose -f docker-compose.oracle.yml --env-file .env up -d
```

필수 값:

- `DATABASE_URL`: PostgreSQL 연결 문자열
- `NEXT_PUBLIC_API_BASE_URL`: 브라우저에서 접근 가능한 백엔드 주소

## Oracle Cloud Container Registry 예시

```bash
docker build -t trenda-backend:latest ./backend
docker build \
  --build-arg NEXT_PUBLIC_API_BASE_URL=http://YOUR_ORACLE_PUBLIC_IP:8080 \
  -t trenda-frontend:latest ./frontend
```

Registry에 올릴 때는 Oracle Cloud의 namespace, region key, repository 이름에 맞춰 태그를 바꿉니다.

```bash
docker tag trenda-backend:latest <region-key>.ocir.io/<namespace>/trenda-backend:latest
docker tag trenda-frontend:latest <region-key>.ocir.io/<namespace>/trenda-frontend:latest
docker push <region-key>.ocir.io/<namespace>/trenda-backend:latest
docker push <region-key>.ocir.io/<namespace>/trenda-frontend:latest
```

## 헬스 체크

백엔드는 Actuator 헬스 체크를 사용합니다.

```bash
curl http://YOUR_ORACLE_PUBLIC_IP:8080/actuator/health
```
