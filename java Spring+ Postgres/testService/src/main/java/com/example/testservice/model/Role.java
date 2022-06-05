package com.example.testservice.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity // указание для БД, что класс является сущностью
@Getter
@Setter // создает geter/setter методы для полей объекта role
@NoArgsConstructor // создает пустой конструктор
@Table(name = "roles")
public class Role implements GrantedAuthority { // Сущность роли

    @Id
    private Long id;

    private String name;

    @Transient
    @ManyToMany(mappedBy = "roles")
    private Set<User> users; // Подгрузка ролей пользователя для манипуляций в приложении(не сохраняется БД)

    public Role(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String getAuthority() {
        return getName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return Objects.equals(name, role.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
