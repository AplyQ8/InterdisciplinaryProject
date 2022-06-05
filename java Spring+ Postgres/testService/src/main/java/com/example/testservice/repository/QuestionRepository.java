package com.example.testservice.repository;

import com.example.testservice.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

// Интерфейс для обращения в БД к таблице Question
// Технология Spring JPA
public interface QuestionRepository extends JpaRepository<Question, Long> {
    Question findByQuestion(String title);
}
