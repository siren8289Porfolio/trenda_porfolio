package com.example.service.profile.dto;

import com.example.service.playrecord.dto.PlayRecordResponse;
import com.example.service.portfolio.dto.PortfolioResponse;
import com.example.service.roadmap.dto.RoadmapResponse;
import com.example.service.user.dto.UserResponse;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/** 프로필 조회 API 응답 DTO. Entity 노출 방지, 스펙 명확화. */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfileResponse {

    private UserResponse user;
    private List<PlayRecordResponse> playRecords;
    private List<PortfolioResponse> portfolios;
    private List<RoadmapResponse> roadmaps;

    /** 여러 도메인 조회 결과를 하나의 응답 DTO로 조합. */
    public static ProfileResponse from(
            UserResponse user,
            List<PlayRecordResponse> playRecords,
            List<PortfolioResponse> portfolios,
            List<RoadmapResponse> roadmaps) {
        return ProfileResponse.builder()
                .user(user)
                .playRecords(playRecords == null ? List.of() : playRecords)
                .portfolios(portfolios == null ? List.of() : portfolios)
                .roadmaps(roadmaps == null ? List.of() : roadmaps)
                .build();
    }
}
