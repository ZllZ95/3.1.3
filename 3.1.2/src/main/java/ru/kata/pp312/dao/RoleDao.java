package ru.kata.pp312.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kata.pp312.model.Role;

public interface RoleDao extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
