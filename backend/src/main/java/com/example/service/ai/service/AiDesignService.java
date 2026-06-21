package com.example.service.ai.service;

import com.example.service.ai.dto.AiDesignRequest;
import com.example.service.ai.dto.AiDesignResponse;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class AiDesignService {

    public AiDesignResponse generate(AiDesignRequest request) {
        String prompt = request.getPrompt().trim();
        String audience = clean(request.getAudience(), "디자인/개발 전공자");
        String tone = clean(request.getTone(), "실용적이고 선명한");

        return AiDesignResponse.builder()
                .title("AI 제안: " + shorten(prompt))
                .summary(String.format(
                        "%s 톤으로 %s에게 맞춘 화면 구조입니다. 핵심 행동을 먼저 보여주고 프로젝트 증거와 다음 액션을 이어 붙입니다.",
                        tone,
                        audience
                ))
                .sections(List.of(
                        "첫 화면: 문제, 대상, 핵심 가치 한 줄",
                        "추천 영역: 관심 기술과 저장한 트렌드 기반 카드",
                        "작업 영역: 프로젝트 템플릿, 체크리스트, 포트폴리오 저장",
                        "검증 영역: 스킬 테스트 결과와 성장 그래프"
                ))
                .components(List.of(
                        "TrendCard",
                        "SkillLevelBadge",
                        "RoadmapTimeline",
                        "PortfolioResourcePicker",
                        "AiReviewPanel"
                ))
                .palette(List.of("#1CB0F6", "#58CC02", "#FFB020", "#111827", "#FFFBF7"))
                .nextSteps(List.of(
                        "프론트에서 /api/ai/design 응답을 카드로 렌더링",
                        "저장 버튼은 /api/roadmap 또는 /api/portfolio와 연결",
                        "실제 AI 모델 키가 준비되면 AiDesignService 내부 구현만 교체"
                ))
                .build();
    }

    private String clean(String value, String fallback) {
        if (value == null || value.trim().isEmpty()) {
            return fallback;
        }
        return value.trim();
    }

    private String shorten(String value) {
        if (value.length() <= 40) {
            return value;
        }
        return value.substring(0, 40) + "...";
    }
}
