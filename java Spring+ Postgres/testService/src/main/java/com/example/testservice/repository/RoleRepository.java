package com.example.testservice.repository;

import com.example.testservice.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

// Интерфейс для обращения в БД к таблице Role
// Технология Spring JPA
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
