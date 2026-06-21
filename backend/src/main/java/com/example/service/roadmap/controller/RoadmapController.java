package com.example.service.roadmap.controller;

import com.example.service.roadmap.dto.RoadmapCreateRequest;
import com.example.service.roadmap.dto.RoadmapResponse;
import com.example.service.roadmap.dto.RoadmapUpdateRequest;
import com.example.service.roadmap.service.RoadmapService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/roadmap")
@RequiredArgsConstructor
public class RoadmapController {

    private final RoadmapService roadmapService;

    @GetMapping("/{userId}")
    public List<RoadmapResponse> getMyRoadmap(@PathVariable Long userId) {
        return roadmapService.getUserRoadmaps(userId);
    }

    @GetMapping("/items/{id}")
    public RoadmapResponse detail(@PathVariable Long id) {
        return roadmapService.getRoadmap(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RoadmapResponse addRoadmap(@Valid @RequestBody RoadmapCreateRequest request) {
        return roadmapService.createRoadmap(request);
    }

    @PostMapping(params = {"userId", "title", "content"})
    @ResponseStatus(HttpStatus.CREATED)
    public RoadmapResponse addRoadmapFromParams(
            @RequestParam Long userId,
            @RequestParam String title,
            @RequestParam String content
    ) {
        return roadmapService.createRoadmap(new RoadmapCreateRequest(userId, title, content));
    }

    @PutMapping("/items/{id}")
    public RoadmapResponse update(
            @PathVariable Long id,
            @Valid @RequestBody RoadmapUpdateRequest request
    ) {
        return roadmapService.updateRoadmap(id, request);
    }

    @DeleteMapping("/items/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        roadmapService.deleteRoadmap(id);
    }
}
