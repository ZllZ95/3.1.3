package ru.kata.pp312.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kata.pp312.model.Role;
import ru.kata.pp312.repository.RoleRepository;
import ru.kata.pp312.service.RoleService;


import java.util.List;

@Service

public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override

    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override

    public Role getRoleByName(String name) {
        return roleRepository.findByName(name);
    }
}
