package com.youth.discord.scheduler;

import com.youth.discord.service.DiscordService;
import com.youth.discord.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class DiscordScheduler {

    private final QuizService quizService;
    private final DiscordService discordService;

    @Transactional
    @Scheduled(cron = "0 0 16 * * MON")
    public void sendQuiz() {
        discordService.sendMessageToTextChannel(discordService.messageEmbedFrom(quizService.pickRandomQuiz()));
    }
}
