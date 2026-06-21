package com.example.service.user.controller;

import com.example.service.common.response.ApiResponse;
import com.example.service.user.dto.*;
import com.example.service.user.service.UserService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // [0] 페이징 결과 반환 (리뷰 반영)
    @GetMapping
    public ApiResponse<Page<UserResponse>> findAll(Pageable pageable) {
        return ApiResponse.success(userService.findAll(pageable));
    }

    // [0] 검색 DTO(UserSearchRequest) 사용 (리뷰 반영)
    @GetMapping("/search")
    public ApiResponse<UserResponse> search(@ModelAttribute UserSearchRequest condition) {
        return ApiResponse.success(userService.search(condition));
    }

    @GetMapping("/{id}")
    public ApiResponse<UserResponse> findById(@PathVariable Long id) {
        return ApiResponse.success(userService.getById(id));
    }

    // [0] 201 CREATED + ResponseEntity 적용 (리뷰 반영)
    @PostMapping
    public ResponseEntity<ApiResponse<UserResponse>> create(
            @Valid @RequestBody UserCreateRequest request
    ) {
        UserResponse response = userService.create(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success(response));
    }

    @PatchMapping("/{id}")
    public ApiResponse<UserResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody UserUpdateRequest request
    ) {
        return ApiResponse.success(userService.update(id, request));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }
}
