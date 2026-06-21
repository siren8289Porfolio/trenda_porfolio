package com.example.service.question.controller;

import com.example.service.common.response.ApiResponse;
import com.example.service.question.dto.QuestionCreateRequest;
import com.example.service.question.dto.QuestionResponse;
import com.example.service.question.service.QuestionService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/play/questions")
public class QuestionController {

    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("/game/{gameId}")
    public ApiResponse<List<QuestionResponse>> findByGame(@PathVariable Long gameId) {
        return ApiResponse.success(questionService.findByGame(gameId));
    }

    @PostMapping
    public ApiResponse<QuestionResponse> create(@Valid @RequestBody QuestionCreateRequest request) {
        return ApiResponse.success(questionService.save(request));
    }
}
