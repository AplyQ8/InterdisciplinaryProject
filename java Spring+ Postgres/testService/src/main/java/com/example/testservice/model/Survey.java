package com.example.testservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Objects;

@Getter
@Setter // создает getter/setter методы для полей объекта survey
@NoArgsConstructor // создает пустой конструктор
@AllArgsConstructor // создает конструктор для всех аргументов класса
@Entity // указание для БД, что класс является сущностью
@Table(name = "survey")
public class Survey { // Сущность тестов

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title cannot be empty")
    @Column(name = "title")
    private String title;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "survey_questions",
            joinColumns = {@JoinColumn(name = "survey_id")},
            inverseJoinColumns = {@JoinColumn(name = "question_id")})
    private List<Question> questionList; // Список вопросов для теста

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Survey survey = (Survey) o;
        return Objects.equals(id, survey.id) && Objects.equals(title, survey.title) && Objects.equals(questionList, survey.questionList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, questionList);
    }
}
