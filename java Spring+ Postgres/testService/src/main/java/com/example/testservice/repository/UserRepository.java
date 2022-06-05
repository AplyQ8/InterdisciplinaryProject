package com.example.testservice.repository;

import com.example.testservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

// Интерфейс для обращения в БД к таблице User
// Технология Spring JPA
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
