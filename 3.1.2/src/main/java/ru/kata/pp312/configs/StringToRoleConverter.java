package ru.kata.pp312.configs;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.kata.pp312.model.Role;
import ru.kata.pp312.repository.RoleRepository;

@Component
public class StringToRoleConverter implements Converter<String, Role> {

    private final RoleRepository roleRepository;

    public StringToRoleConverter(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role convert(String id) {
        return roleRepository.findById(Long.parseLong(id)).orElse(null);
    }
}
