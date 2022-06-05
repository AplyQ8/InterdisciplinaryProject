package com.example.testservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Getter
@Setter // создает geter/setter методы для полей объекта question
@NoArgsConstructor // создает пустой конструктор
@AllArgsConstructor // создает конструктор для всех аргументов класса
@Entity // указание для БД, что класс является сущностью
@Table(name = "question")
public class Question { // Сущность вопроса для теста
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String question;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Answer> answers;
}
