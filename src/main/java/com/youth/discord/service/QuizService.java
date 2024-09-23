package com.youth.discord.service;

import com.youth.discord.entity.Quiz;
import com.youth.discord.repository.QuizRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class QuizService {

    private final QuizRepository quizRepository;

    @Transactional(readOnly = true)
    public Quiz pickRandomQuiz() {
        return quizRepository.findById(1L).orElseThrow(() -> new RuntimeException("퀴즈 없음"));
    }
}
