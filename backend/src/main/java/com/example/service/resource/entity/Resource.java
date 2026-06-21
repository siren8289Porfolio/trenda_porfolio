package com.example.service.resource.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Resource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;       // 자료 제목 (예: "자바 기초 강의")
    private String url;         // 링크 주소 (예: "https://youtube.com/...")
    private String category;    // 카테고리 (예: "VIDEO", "BLOG", "DOC")
    private String description; // 자료 설명

    public Resource(String title, String url, String category, String description) {
        this.title = title;
        this.url = url;
        this.category = category;
        this.description = description;
    }

    public void update(String title, String url, String category, String description) {
        this.title = title;
        this.url = url;
        this.category = category;
        this.description = description;
    }
}
