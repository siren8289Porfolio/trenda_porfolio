package com.example.service.playrecord.controller;

import com.example.service.common.response.ApiResponse;
import com.example.service.playrecord.dto.PlayRecordCreateRequest;
import com.example.service.playrecord.dto.PlayRecordResponse;
import com.example.service.playrecord.dto.PlayRecordUpdateRequest;
import com.example.service.playrecord.service.PlayRecordService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
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

    @GetMapping("/{id}")
    public ApiResponse<PlayRecordResponse> detail(@PathVariable Long id) {
        return ApiResponse.success(playRecordService.getById(id));
    }

    @PostMapping
    public ApiResponse<PlayRecordResponse> record(@RequestBody @Valid PlayRecordCreateRequest request) {
        return ApiResponse.success(playRecordService.recordPlay(request));
    }

    @PatchMapping("/{id}")
    public ApiResponse<PlayRecordResponse> update(
            @PathVariable Long id,
            @RequestBody @Valid PlayRecordUpdateRequest request
    ) {
        return ApiResponse.success(playRecordService.updateScore(id, request));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        playRecordService.delete(id);
    }
}
