package com.youth.discord.controller;

import com.youth.discord.service.DiscordService;
import com.youth.discord.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
@RequiredArgsConstructor
public class TestController {

    private final QuizService quizService;
    private final DiscordService discordService;


    @PostMapping("/send")
    public ResponseEntity<Void> sendRandomQuiz() {
        discordService.sendMessageToTextChannel(discordService.messageEmbedFrom(quizService.pickRandomQuiz()));
        return ResponseEntity.ok().build();
    }
}
