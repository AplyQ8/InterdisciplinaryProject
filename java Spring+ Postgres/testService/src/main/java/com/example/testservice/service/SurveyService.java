package com.example.testservice.service;

import com.example.testservice.model.Answer;
import com.example.testservice.model.Question;
import com.example.testservice.model.Survey;
import com.example.testservice.model.User;
import com.example.testservice.repository.AnswerRepository;
import com.example.testservice.repository.QuestionRepository;
import com.example.testservice.repository.SurveyRepository;
import com.example.testservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// Сервисный слой с логикой запросов к БД
@Service // аннотация указывает Spring,что класс является сервисом
@RequiredArgsConstructor // создает конструктор с нужными параметрами
public class SurveyService {
    @Autowired
    private SurveyRepository surveyRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AnswerRepository answerRepository;
    @Autowired
    private QuestionRepository questionRepository;


    // Метод создания теста. Входные параметры - текущий пользователь,
    // название теста
    public Survey createSurvey(User user,String title){
        Survey survey = new Survey();
        survey.setTitle(title);
        surveyRepository.save(survey);
        Set<Survey> userSurveys = new HashSet<>(user.getSurveys());
        Survey surveyToAdd = surveyRepository.findByTitle(title);
        userSurveys.add(surveyToAdd);
        user.setSurveys(userSurveys);
        userRepository.save(user);
        return survey;
    }

    // Метод удаления опроса для администратора
    public void deleteSurvey(Long id,User user){
        Set<Survey> surveyList = new HashSet<>(user.getSurveys());
        surveyList.removeIf(survey -> survey.getId().equals(id));
        user.setSurveys(surveyList);
        userRepository.save(user);
    }

    // Метод получения теста по ID
    public Survey getSurveyById(Long id){
        return surveyRepository.getById(id);
    }


    // Метод поиска создателя теста
    public Long findOwner(Long id){
        Long userID = 0L;
        Survey survey = surveyRepository.getById(id);
        for (User user : userRepository.findAll()) {
            if (user.getSurveys().contains(survey)){
                userID = user.getId();
            }
        }
        return userID;
    }


    // метод добавления в тест вопроса
    public void addQuestionToSurveyById(Long id, Question question){
        Survey survey = surveyRepository.getById(id);
        List<Question> questionList = new ArrayList<>(survey.getQuestionList());
        questionList.add(question);
        survey.setQuestionList(questionList);
        surveyRepository.save(survey);
    }

    // метод для создания вопроса и последующего добавления в тест
    public Question createQuestion(String title, List<String> answers){
        Question question = new Question();
        question.setQuestion(title);
        List<Answer> answerList = new ArrayList<>();
        for (String ans: answers) {
            Answer answer = new Answer();
            answer.setAnswer(ans);
            answerRepository.save(answer);
            List<Answer> find = answerRepository.findAllByAnswer(ans);
            answerList.add(find.get(find.size()-1));
        }
        question.setAnswers(answerList);
        questionRepository.save(question);

        return questionRepository.findByQuestion(title);
    }


    // Метод поиска теста по названию и автору
    public Survey findSurveyByTitleAndUser(User user, String title){
        Set<Survey> surveys = new HashSet<>(user.getSurveys());
        surveys.removeIf(survey -> !survey.getTitle().equals(title));
        List<Survey> surveyList = new ArrayList<>(surveys);
        return surveyList.get(0);
    }


    // Метод проверяет, является ли текущий пользователь создателем теста
    public boolean isOwner(User user,Long id){
        Survey survey = surveyRepository.getById(id);
        return user.getSurveys().stream().anyMatch(survey1 -> survey1.getId().equals(survey.getId()));
    }

}
