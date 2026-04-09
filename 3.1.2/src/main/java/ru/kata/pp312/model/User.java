package ru.kata.pp312.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import lombok.Data;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import java.util.Collection;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "username", nullable = false, unique = true)
    @NotEmpty(message = "Имя не должно быть пустым")
    @Size(min = 2, max = 20, message = "Длина имени должна быть от 2 до 20 символов")
    private String username;


    @Column(name = "age", nullable = false)
    @NotNull(message = "Возраст не должно быть пустым")
    @Min(value = 0, message = "Возраст должен быть больше 0")
    @Max(value = 110, message = "Возраст не должен быть больше 110")
    private Integer age;


    @NotEmpty(message = "Email не должен быть пустым")
    @Email(message = "Должен быть Email")
    @Column(name = "email", nullable = false)
    private String email;

    @Column
    private String password;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();


    public User() {

    }

    public User(int id, String name, Integer age, String email, String password, Set<Role> roles) {
        this.id = id;
        this.username = name;
        this.age = age;
        this.email = email;
        this.password = password;
        this.roles = roles != null ? roles : new HashSet<>();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
                .map(role -> (GrantedAuthority) role)
                .toList();
    }

    @Override
    public @Nullable String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    
    
}



