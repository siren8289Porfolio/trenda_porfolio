package com.example.service.game.service;

import com.example.service.common.exception.CustomException;
import com.example.service.game.dto.GameCreateRequest;
import com.example.service.game.dto.GameResponse;
import com.example.service.game.dto.GameUpdateRequest;
import com.example.service.game.entity.Game;
import com.example.service.game.repository.GameRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Game 도메인 Service.
 * - 비즈니스 유스케이스와 트랜잭션 경계를 담당하고, DTO 를 반환한다.
 */
@Service
@Transactional(readOnly = true)
public class GameService {

    private final GameRepository gameRepository;

    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
        }

    /** 게임 목록 조회 (Pageable). Entity 대신 GameResponse DTO 로 매핑. */
    public Page<GameResponse> findAll(Pageable pageable) {
        return gameRepository.findAll(pageable).map(GameResponse::from);
    }

    /** ID 기준 단건 조회. 존재하지 않으면 REST 규격에 맞는 CustomException 발생. */
    public Game findById(Long id) {
        return gameRepository.findById(id)
                .orElseThrow(() -> new CustomException("Game not found", HttpStatus.NOT_FOUND));
    }

    public GameResponse getById(Long id) {
        return GameResponse.from(findById(id));
    }

    /**
     * 게임 생성.
     * - Controller 에서는 DTO만 받고,
     * - Service 에서 Entity 생성 및 저장을 책임진다.
     */
    @Transactional
    public GameResponse create(GameCreateRequest request) {
        Game game = Game.create(
                request.normalizedTitle(),
                request.normalizedDescription()
        );
        return GameResponse.from(gameRepository.save(game));
    }

    @Transactional
    public GameResponse update(Long id, GameUpdateRequest request) {
        Game game = findById(id);
        game.update(request.normalizedTitle(), request.normalizedDescription());
        return GameResponse.from(game);
    }

    @Transactional
    public void delete(Long id) {
        gameRepository.delete(findById(id));
    }
}
