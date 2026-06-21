package com.example.service.ai.dto;

import java.util.List;
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
public class AiDesignResponse {

    private String title;
    private String summary;
    private List<String> sections;
    private List<String> components;
    private List<String> palette;
    private List<String> nextSteps;
}
