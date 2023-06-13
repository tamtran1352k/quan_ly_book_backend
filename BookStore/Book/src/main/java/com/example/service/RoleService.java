package com.example.service;

import com.example.model.Role;

import java.util.List;

public interface RoleService {
    List<Role> getAll();

    Role add(Role dto);

    void delete(Integer id);

    Role findById(Integer id);

    Role update(Integer id, Role dto);
}