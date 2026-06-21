package com.example.service.game.dto;

import jakarta.validation.constraints.Size;
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
public class GameUpdateRequest {

    @Size(max = 100)
    private String title;

    @Size(max = 500)
    private String description;

    public String normalizedTitle() {
        return title == null ? null : title.trim();
    }

    public String normalizedDescription() {
        return description == null ? null : description.trim();
    }
}
