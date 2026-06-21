package com.example.service.roadmap.dto;

import com.example.service.roadmap.entity.Roadmap;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoadmapResponse {

    private Long id;
    private Long userId;
    private String title;
    private String content;
    private String stepContent;

    public static RoadmapResponse from(Roadmap roadmap) {
        if (roadmap == null) {
            return null;
        }
        return RoadmapResponse.builder()
                .id(roadmap.getId())
                .userId(roadmap.getUserId())
                .title(roadmap.getTitle())
                .content(roadmap.getStepContent())
                .stepContent(roadmap.getStepContent())
                .build();
    }
}
