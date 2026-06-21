package com.example.service.playrecord.service;

import com.example.service.game.entity.Game;
import com.example.service.game.service.GameService;
import com.example.service.playrecord.dto.PlayRecordCreateRequest;
import com.example.service.playrecord.dto.PlayRecordResponse;
import com.example.service.playrecord.entity.PlayRecord;
import com.example.service.playrecord.repository.PlayRecordRepository;
import com.example.service.user.entity.User;
import com.example.service.user.service.UserService;
import java.util.List;
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
}
