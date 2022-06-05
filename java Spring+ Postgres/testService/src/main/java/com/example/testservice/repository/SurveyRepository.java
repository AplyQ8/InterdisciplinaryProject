package com.example.testservice.repository;

import com.example.testservice.model.Survey;
import org.springframework.data.jpa.repository.JpaRepository;

// Интерфейс для обращения в БД к таблице Survey
// Технология Spring JPA
public interface SurveyRepository extends JpaRepository<Survey, Long> {
    Survey findByTitle(String title);


}
