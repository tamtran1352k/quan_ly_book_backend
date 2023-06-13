package com.example.service.impl;

import com.example.model.Role;
import com.example.repository.RoleRepository;
import com.example.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleRepository roleRepository;


    @Override
    public List<Role> getAll() {

        return roleRepository.findAll();
    }

    @Override
    public Role add(Role model) {
        Role check = roleRepository.findByName(model.getName()).orElse(null);
        if (check == null) {
            return roleRepository.save(model);
        }
        return null;

    }

    @Override
    public void delete(Integer id) {
        roleRepository.deleteById(id);
    }

    @Override
    public Role findById(Integer id) {
        return roleRepository.findById(id).orElse(null);
    }

    @Override
    public Role update(Integer id, Role role) {

        // get from database by id
        Role existing = roleRepository.findById(id).orElse(null);
        existing.setName(role.getName());
        existing.setDescription(role.getDescription());
        return roleRepository.save(existing);
    }
}
