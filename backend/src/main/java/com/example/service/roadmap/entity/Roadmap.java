package com.example.service.roadmap.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Roadmap {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;       // 대상 유저 번호
    private String title;      // 로드맵 제목 (예: "백엔드 개발자 입문 코스")
    private String stepContent; // 단계별 상세 설명 (예: "1단계: 자바 기초, 2단계: 스프링")

    public Roadmap(Long userId, String title, String stepContent) {
        this.userId = userId;
        this.title = title;
        this.stepContent = stepContent;
    }

    public void update(String title, String stepContent) {
        this.title = title;
        this.stepContent = stepContent;
    }
}
