package com.example.testservice.controller;

import com.example.testservice.model.Survey;
import com.example.testservice.model.User;
import com.example.testservice.repository.RoleRepository;
import com.example.testservice.repository.SurveyRepository;
import com.example.testservice.service.FileExporter;
import com.example.testservice.service.SurveyService;
import com.example.testservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

// Контроллер - класс, предназначенный для непосредственной
// обработки запросов от клиента и возвращения результатов.
@Controller
public class SurveyController {

    @Autowired
    private SurveyService surveyService;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private SurveyRepository surveyRepository;

    @Autowired
    private FileExporter fileExporter;


    // запрос на получение теста по ID
    @GetMapping("/survey/{id}")
    public String getSurvey(@PathVariable Long id, Model model, @AuthenticationPrincipal User user){
        model.addAttribute("survey",surveyService.getSurveyById(id));
        model.addAttribute("isAdmin",userService.isAdmin(user));
        model.addAttribute("user",userService.getUserById(surveyService.findOwner(id)));
        model.addAttribute("isOwner",surveyService.isOwner(user,id));
        return "survey";
    }


    // запрос на удаление теста по ID
    @PostMapping("/delete/survey/{id}")
    public String deleteSurveyById(@PathVariable Long id, @AuthenticationPrincipal User currentUser){
        Long ownerId = surveyService.findOwner(id);
        surveyService.deleteSurvey(id,userService.getUserById(ownerId));
        User user = userService.getUserById(ownerId);
        if (!user.equals(currentUser)){
            return "redirect:/user/" + ownerId;
        }
        return "redirect:/surveys";
    }


    // Запрос на создание теста
    @GetMapping("/createSurvey")
    public String createSurvey(@AuthenticationPrincipal User user, Model model){
        model.addAttribute("user",user);
        return "createSurvey";
    }


    // Метод, реализующий запрос создание теста
    @PostMapping("/create")
    public String create(@AuthenticationPrincipal User user, @RequestParam String title){
        surveyService.createSurvey(user,title);
        Survey survey = surveyService.findSurveyByTitleAndUser(user,title);
        return "redirect:/addQuestion/" + survey.getId();
    }


    // Запрос на добавление вопроса к тесту
    @GetMapping("/addQuestion/{id}")
    public String addQuestion(@PathVariable Long id,Model model){
        model.addAttribute("id",id);
        return "addQuestion";
    }


    // Метод, реализующий запрос на добавление вопроса к тесту
    @PostMapping("/add/{id}")
    public String add(@PathVariable Long id, @RequestParam List<String> answers, @RequestParam String title){
        surveyService.addQuestionToSurveyById(id,surveyService.createQuestion(title,answers));
        return "redirect:/addQuestion/" + id;
    }


    // Запрос на скачивание теста в формате PDF
    @GetMapping("/survey/{surveyId}/export/pdf")
    public void exportToPDF(HttpServletResponse response,@PathVariable Long surveyId) throws Exception {
        Survey survey = surveyService.getSurveyById(surveyId);
        User user = userService.getUserById(surveyService.findOwner(surveyId));
        fileExporter.exportToPDF(user,survey,response);
    }
}
