package com.example.service.resource.dto;

import jakarta.validation.constraints.NotBlank;
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
public class ResourceUpdateRequest {

    @NotBlank
    private String title;

    @NotBlank
    private String url;

    @NotBlank
    private String category;

    private String description;
}
