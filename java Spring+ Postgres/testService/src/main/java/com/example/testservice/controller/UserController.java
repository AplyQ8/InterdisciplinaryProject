package com.example.testservice.controller;

import com.example.testservice.model.Survey;
import com.example.testservice.model.User;
import com.example.testservice.repository.UserRepository;
import com.example.testservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashSet;
import java.util.Set;


// Контроллер - класс, предназначенный для непосредственной
// обработки запросов от клиента и возвращения результатов.
@Controller
public class UserController {


    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    // Запрос на отображение главной страницы
    @GetMapping("/home")
    public String main(Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("user",user);
        model.addAttribute("isAdmin",userService.isAdmin(user));
        return "home";
    }

    // Запрос на авторизацию
    @GetMapping("/login")
    public String login() {
        return "log";
    }


    // Запрос на регистрацию
    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }


    // Метод, реализующий запрос на регистрацию
    @PostMapping("/registration")
    public String createUser(User user, Model model) {
        if(!userService.registration(user)) {
            model.addAttribute("errorMessage", "Пользователь с таким логином уже существует");
            return "registration";
        }
        return "redirect:/log";
    }

    // Запрос на получение профиля текущего пользователя
    @GetMapping("/profile")
    public String getProfile(@AuthenticationPrincipal User user, Model model){
        model.addAttribute("user",user);
        return "profile";
    }

    // Запрос на получение списка тестов текущего пользователя
    @GetMapping("/surveys")
    public String getSurveys(@AuthenticationPrincipal User user, Model model){
        model.addAttribute("surveys",userRepository.getById(user.getId()).getSurveys());
        model.addAttribute("user",user);
        return "surveys";
    }

    // Запрос на получения списка тестов конкретного пользователя(по ID)
    @GetMapping("/surveys/{id}")
    public String getUserSurveysList(@PathVariable Long id, Model model){
        model.addAttribute("surveys",userRepository.getById(id).getSurveys());
        return "surveys";
    }


    // Запрос на удаление пользователя(может только админ)
    @PostMapping("delete/user/{id}")
    public String deleteUserById(@PathVariable Long id){
        userService.deleteUser(id);
        return "redirect:/users";
    }

    // Запрос на получение списка всех пользователей(для админа)
    @GetMapping("/users")
    public String getAllUsers(Model model){
        model.addAttribute("users", userRepository.findAll());
        return "users";
    }


    // Запрос на переименование пользователя(может только админ)
    @GetMapping("/user/rename/{id}")
    public String renameUser(@PathVariable Long id, Model model){
        model.addAttribute("user",userRepository.getById(id));
        return "renameUser";
    }


    // метод, реализующий запрос на переименование пользователя
    @PostMapping("/rename/{id}")
    public String rename(@PathVariable Long id, @RequestParam String username, Model model){
        if(!userService.changeUsernameById(id,username)){
            model.addAttribute("errorMessage", "Пользователь с таким логином уже существует");
        }
        return "redirect:/user/{id}";
    }


    @GetMapping("/user/{id}")
    public String getUser(@PathVariable Long id, Model model){
        model.addAttribute("user",userService.getUserById(id));
        return "user";
    }

}
