package com.example.service.playrecord.service;

import com.example.service.common.exception.CustomException;
import com.example.service.game.entity.Game;
import com.example.service.game.service.GameService;
import com.example.service.playrecord.dto.PlayRecordCreateRequest;
import com.example.service.playrecord.dto.PlayRecordResponse;
import com.example.service.playrecord.dto.PlayRecordUpdateRequest;
import com.example.service.playrecord.entity.PlayRecord;
import com.example.service.playrecord.repository.PlayRecordRepository;
import com.example.service.user.entity.User;
import com.example.service.user.service.UserService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class PlayRecordService {

    private final PlayRecordRepository playRecordRepository;
    private final UserService userService;
    private final GameService gameService;

    public PlayRecordService(
            PlayRecordRepository playRecordRepository,
            UserService userService,
            GameService gameService) {
        this.playRecordRepository = playRecordRepository;
        this.userService = userService;
        this.gameService = gameService;
    }

    public List<PlayRecordResponse> findByUser(Long userId) {
        return playRecordRepository.findByUserId(userId)
                .stream()
                .map(PlayRecordResponse::from)
                .toList();
    }

    public PlayRecordResponse getById(Long id) {
        return PlayRecordResponse.from(getPlayRecordEntity(id));
    }

    @Transactional
    public PlayRecordResponse recordPlay(PlayRecordCreateRequest request) {
        User user = userService.getUserEntity(request.getUserId());
        Game game = gameService.findById(request.getGameId());

        PlayRecord record = PlayRecord.builder()
                .user(user)
                .game(game)
                .score(request.getScore())
                .build();

        return PlayRecordResponse.from(playRecordRepository.save(record));
    }

    @Transactional
    public PlayRecordResponse updateScore(Long id, PlayRecordUpdateRequest request) {
        PlayRecord record = getPlayRecordEntity(id);
        record.updateScore(request.getScore());
        return PlayRecordResponse.from(record);
    }

    @Transactional
    public void delete(Long id) {
        playRecordRepository.delete(getPlayRecordEntity(id));
    }

    private PlayRecord getPlayRecordEntity(Long id) {
        return playRecordRepository.findById(id)
                .orElseThrow(() -> new CustomException("PLAY_RECORD_NOT_FOUND", "Play record not found", HttpStatus.NOT_FOUND));
    }
}
