package com.example.service.ai.controller;

import com.example.service.ai.dto.AiDesignRequest;
import com.example.service.ai.dto.AiDesignResponse;
import com.example.service.ai.service.AiDesignService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ai")
@RequiredArgsConstructor
public class AiController {

    private final AiDesignService aiDesignService;

    @PostMapping("/design")
    public AiDesignResponse generateDesign(@Valid @RequestBody AiDesignRequest request) {
        return aiDesignService.generate(request);
    }
}
