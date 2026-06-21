package com.example.service.game.controller;

import com.example.service.common.response.ApiResponse;
import com.example.service.game.dto.GameCreateRequest;
import com.example.service.game.dto.GameResponse;
import com.example.service.game.dto.GameUpdateRequest;
import com.example.service.game.service.GameService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * 게임 API 컨트롤러.
 * - HTTP 입출력만 담당하고, 비즈니스 로직·Entity 타입은 Service/Domain 에 위임한다.
 */
@RestController
@RequestMapping("/api/play/games")
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    /** 게임 목록 조회. Pageable 기반, Service 가 DTO 를 반환하도록 위임. */
    @GetMapping
    public ApiResponse<Page<GameResponse>> list(Pageable pageable) {
        return ApiResponse.success(gameService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ApiResponse<GameResponse> detail(@PathVariable Long id) {
        return ApiResponse.success(gameService.getById(id));
    }

    /** 게임 생성. Request DTO만 받고, 응답은 GameResponse DTO로 고정. */
    @PostMapping
    public ApiResponse<GameResponse> create(@Valid @RequestBody GameCreateRequest request) {
        return ApiResponse.success(gameService.create(request));
    }

    @PatchMapping("/{id}")
    public ApiResponse<GameResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody GameUpdateRequest request
    ) {
        return ApiResponse.success(gameService.update(id, request));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        gameService.delete(id);
    }
}
