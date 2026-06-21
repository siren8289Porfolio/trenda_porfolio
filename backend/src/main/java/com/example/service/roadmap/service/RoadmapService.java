package com.example.service.roadmap.service;

import com.example.service.roadmap.dto.RoadmapCreateRequest;
import com.example.service.roadmap.dto.RoadmapResponse;
import com.example.service.roadmap.entity.Roadmap;
import com.example.service.roadmap.repository.RoadmapRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoadmapService {

    private final RoadmapRepository roadmapRepository;

    public List<RoadmapResponse> getUserRoadmaps(Long userId) {
        return roadmapRepository.findByUserId(userId)
                .stream()
                .map(RoadmapResponse::from)
                .toList();
    }

    public RoadmapResponse createRoadmap(RoadmapCreateRequest request) {
        Roadmap roadmap = new Roadmap(
                request.getUserId(),
                request.getTitle().trim(),
                request.getContent().trim()
        );
        return RoadmapResponse.from(roadmapRepository.save(roadmap));
    }
}
