package com.example.service.question.controller;

import com.example.service.common.response.ApiResponse;
import com.example.service.question.dto.QuestionCreateRequest;
import com.example.service.question.dto.QuestionResponse;
import com.example.service.question.dto.QuestionUpdateRequest;
import com.example.service.question.service.QuestionService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
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

    @GetMapping("/{id}")
    public ApiResponse<QuestionResponse> detail(@PathVariable Long id) {
        return ApiResponse.success(questionService.getById(id));
    }

    @PostMapping
    public ApiResponse<QuestionResponse> create(@Valid @RequestBody QuestionCreateRequest request) {
        return ApiResponse.success(questionService.save(request));
    }

    @PutMapping("/{id}")
    public ApiResponse<QuestionResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody QuestionUpdateRequest request
    ) {
        return ApiResponse.success(questionService.update(id, request));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        questionService.delete(id);
    }
}
