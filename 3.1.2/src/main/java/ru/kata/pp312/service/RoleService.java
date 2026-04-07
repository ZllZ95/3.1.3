package ru.kata.pp312.service;


import ru.kata.pp312.model.Role;

import java.util.List;

public interface RoleService {
    List<Role> getAllRoles();
    Role getRoleByName(String name);
}
