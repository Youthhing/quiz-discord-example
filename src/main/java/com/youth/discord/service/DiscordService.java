package com.youth.discord.service;

import com.youth.discord.entity.Quiz;
import java.awt.Color;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class DiscordService {

    private final JDA jda;

    @Value("${discord.guild.id}")
    private String guildId;

    @Value("${discord.channel.id}")
    private String channelId;

    public void sendMessageToTextChannel(MessageEmbed messageEmbed) {

        Guild guild = Optional.ofNullable(jda.getGuildById(guildId))
                .orElseThrow(() -> new RuntimeException("해당 Id의 길드를 못찾음"));

        TextChannel channel = Optional.ofNullable(guild.getChannelById(TextChannel.class, channelId))
                .orElseThrow(() -> new RuntimeException("해당 채널 못찾음"));

        log.info("Guild 정보: {}", guild);
        log.info("Channel 정보: {}", channel);

        if (channel.canTalk()) {
            channel.sendMessageEmbeds(messageEmbed).queue();
        } else {

            log.warn("채널에 메시지를 보낼 수 없음.");
        }
    }

    public MessageEmbed messageEmbedFrom(Quiz quiz) {
        EmbedBuilder eb = new EmbedBuilder();
        eb.setTitle("오늘의 문제");
        eb.setDescription("누구보다 빠르게 문제를 풀어보세요!");
        eb.setColor(Color.green);
        eb.addField("타입", quiz.getQuizType().toString(), true);
        eb.addField("문제", quiz.getQuestion(), true);
        eb.addField("1번", "하하", true);
        eb.addField("2번", "호호", true);
        return eb.build();
    }
}
