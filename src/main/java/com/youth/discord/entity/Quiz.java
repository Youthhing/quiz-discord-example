package com.youth.discord.entity;

import com.youth.discord.enums.QuizType;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Getter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype", discriminatorType = DiscriminatorType.STRING)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "quiz_id")
    private Long id;

    @Column(name = "quiz_number", nullable = false)
    private Integer number;

    @Column(name = "quiz_question", nullable = false, length = 500)
    private String question;

    @Column(name = "quiz_appear_second")
    private int appearSecond;

    public QuizType getQuizType() {
        if (this instanceof MultipleQuiz) {
            return QuizType.MULTIPLE_QUIZ;
        }
        return QuizType.SHORT_QUIZ;
    }
}
