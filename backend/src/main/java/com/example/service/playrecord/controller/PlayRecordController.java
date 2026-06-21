package com.example.service.playrecord.controller;

import com.example.service.common.response.ApiResponse;
import com.example.service.playrecord.dto.PlayRecordCreateRequest;
import com.example.service.playrecord.dto.PlayRecordResponse;
import com.example.service.playrecord.service.PlayRecordService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/play/records")
public class PlayRecordController {

    private final PlayRecordService playRecordService;

    public PlayRecordController(PlayRecordService playRecordService) {
        this.playRecordService = playRecordService;
    }

    @GetMapping("/user/{userId}")
    public ApiResponse<List<PlayRecordResponse>> findByUser(@PathVariable Long userId) {
        return ApiResponse.success(playRecordService.findByUser(userId));
    }

    @PostMapping
    public ApiResponse<PlayRecordResponse> record(@RequestBody @Valid PlayRecordCreateRequest request) {
        return ApiResponse.success(playRecordService.recordPlay(request));
    }
}
