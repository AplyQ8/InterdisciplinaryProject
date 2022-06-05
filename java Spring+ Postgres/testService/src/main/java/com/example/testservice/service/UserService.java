package com.example.testservice.service;


import com.example.testservice.model.Role;
import com.example.testservice.model.Survey;
import com.example.testservice.model.User;
import com.example.testservice.repository.RoleRepository;
import com.example.testservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


// Сервисный слой с логикой запросов к БД
@Service // аннотация указывает Spring,что класс является сервисом
@RequiredArgsConstructor // создает конструктор с нужными параметрами
public class UserService implements UserDetailsService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    // Загрузка пользователя для Security
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }

    // Регистрация пользователя
    public boolean registration(User user){
        User userDB = userRepository.findByUsername(user.getUsername());
        if (userDB != null) {
            return false;
        }
        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.findByName("USER"));
        user.setRoles(roles);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setUsername(user.getUsername());
        userRepository.save(user);
        return true;
    }

    // Метод удаления пользователя по ID для администратора
    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }

    // Метод изменения имени пользователя по ID для администратора
    public boolean changeUsernameById(Long id, String username){
        User userDB = userRepository.findByUsername(username);
        if (userDB != null) {
            return false;
        }
        User user = userRepository.getById(id);
        user.setUsername(username);
        userRepository.save(user);
        return true;
    }


    // Метод получения пользователя по ID
    public User getUserById(Long id){
        return userRepository.getById(id);
    }


    // Метод проверяет, является ли текущий пользователь администратором
    public boolean isAdmin(User user){
        return user.getRoles().contains(roleRepository.findByName("ADMIN"));
    }

}
