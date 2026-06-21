package com.example.service.profile.service;

import com.example.service.playrecord.dto.PlayRecordResponse;
import com.example.service.playrecord.service.PlayRecordService;
import com.example.service.portfolio.dto.PortfolioResponse;
import com.example.service.portfolio.service.PortfolioService;
import com.example.service.profile.dto.ProfileResponse;
import com.example.service.roadmap.dto.RoadmapResponse;
import com.example.service.roadmap.service.RoadmapService;
import com.example.service.user.dto.UserResponse;
import com.example.service.user.service.UserService;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/** 프로필 조회 유스케이스. User/Play/Portfolio/Roadmap을 조합. 각 도메인 규칙은 각 Service에 위임. */
@Service
@Transactional(readOnly = true)
public class ProfileService {

    private final UserService userService;
    private final PlayRecordService playRecordService;
    private final PortfolioService portfolioService;
    private final RoadmapService roadmapService;

    public ProfileService(
            UserService userService,
            PlayRecordService playRecordService,
            PortfolioService portfolioService,
            RoadmapService roadmapService) {
        this.userService = userService;
        this.playRecordService = playRecordService;
        this.portfolioService = portfolioService;
        this.roadmapService = roadmapService;
    }

    /** 사용자 프로필 화면용 통합 조회. Entity가 아닌 ProfileResponse로 반환. */
    public ProfileResponse getProfile(Long userId) {
        UserResponse user = userService.getById(userId);
        List<PlayRecordResponse> playRecords = playRecordService.findByUser(userId);
        List<PortfolioResponse> portfolios = portfolioService.findByUser(userId);
        List<RoadmapResponse> roadmaps = roadmapService.getUserRoadmaps(userId);
        return ProfileResponse.from(user, playRecords, portfolios, roadmaps);
    }
}
