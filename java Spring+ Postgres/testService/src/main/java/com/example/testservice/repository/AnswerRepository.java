package com.example.testservice.repository;

import com.example.testservice.model.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// Интерфейс для обращения в БД к таблице Answer
// Технология Spring JPA
public interface AnswerRepository extends JpaRepository<Answer,Long> {
    List<Answer> findAllByAnswer(String answer);
}
