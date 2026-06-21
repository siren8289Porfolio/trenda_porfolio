package com.example.service.roadmap.service;

import com.example.service.common.exception.CustomException;
import com.example.service.roadmap.dto.RoadmapCreateRequest;
import com.example.service.roadmap.dto.RoadmapResponse;
import com.example.service.roadmap.dto.RoadmapUpdateRequest;
import com.example.service.roadmap.entity.Roadmap;
import com.example.service.roadmap.repository.RoadmapRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RoadmapService {

    private final RoadmapRepository roadmapRepository;

    public List<RoadmapResponse> getUserRoadmaps(Long userId) {
        return roadmapRepository.findByUserId(userId)
                .stream()
                .map(RoadmapResponse::from)
                .toList();
    }

    public RoadmapResponse getRoadmap(Long id) {
        return RoadmapResponse.from(getRoadmapEntity(id));
    }

    @Transactional
    public RoadmapResponse createRoadmap(RoadmapCreateRequest request) {
        Roadmap roadmap = new Roadmap(
                request.getUserId(),
                request.getTitle().trim(),
                request.getContent().trim()
        );
        return RoadmapResponse.from(roadmapRepository.save(roadmap));
    }

    @Transactional
    public RoadmapResponse updateRoadmap(Long id, RoadmapUpdateRequest request) {
        Roadmap roadmap = getRoadmapEntity(id);
        roadmap.update(request.getTitle().trim(), request.getContent().trim());
        return RoadmapResponse.from(roadmap);
    }

    @Transactional
    public void deleteRoadmap(Long id) {
        roadmapRepository.delete(getRoadmapEntity(id));
    }

    private Roadmap getRoadmapEntity(Long id) {
        return roadmapRepository.findById(id)
                .orElseThrow(() -> new CustomException("ROADMAP_NOT_FOUND", "Roadmap not found", HttpStatus.NOT_FOUND));
    }
}
