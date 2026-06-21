package com.example.service.question.service;

import com.example.service.common.exception.CustomException;
import com.example.service.game.entity.Game;
import com.example.service.game.service.GameService;
import com.example.service.question.dto.QuestionCreateRequest;
import com.example.service.question.dto.QuestionResponse;
import com.example.service.question.dto.QuestionUpdateRequest;
import com.example.service.question.entity.Question;
import com.example.service.question.repository.QuestionRepository;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final GameService gameService;

    public QuestionService(QuestionRepository questionRepository, GameService gameService) {
        this.questionRepository = questionRepository;
        this.gameService = gameService;
    }

    public List<QuestionResponse> findByGame(Long gameId) {
        return questionRepository.findByGameId(gameId)
                .stream()
                .map(QuestionResponse::from)
                .toList();
    }

    public QuestionResponse getById(Long id) {
        return QuestionResponse.from(getQuestionEntity(id));
    }

    @Transactional
    public QuestionResponse save(QuestionCreateRequest request) {
        Game game = gameService.findById(request.getGameId());
        Question question = Question.builder()
                .game(game)
                .content(request.getContent().trim())
                .answer(request.getAnswer())
                .difficulty(request.getDifficulty())
                .build();
        return QuestionResponse.from(questionRepository.save(question));
    }

    @Transactional
    public QuestionResponse update(Long id, QuestionUpdateRequest request) {
        Question question = getQuestionEntity(id);
        question.update(
                request.getContent().trim(),
                request.getAnswer(),
                request.getDifficulty()
        );
        return QuestionResponse.from(question);
    }

    @Transactional
    public void delete(Long id) {
        questionRepository.delete(getQuestionEntity(id));
    }

    private Question getQuestionEntity(Long id) {
        return questionRepository.findById(id)
                .orElseThrow(() -> new CustomException("QUESTION_NOT_FOUND", "Question not found", HttpStatus.NOT_FOUND));
    }
}
