package com.example.service.impl;

import com.example.model.User;
import com.example.repository.RoleRepository;
import com.example.repository.UserRepository;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;


    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User add(User model) {
        User check = userRepository.findByEmail(model.getEmail()).orElse(null);
        if (check != null) {
            return null;
        }
        if (model.getPassword() == null || model.getPassword().isEmpty()) {
            model.setPassword("123456"); //mặc định
        }
        System.out.println(model.getPassword());
//        String hashed = BCrypt.hashpw(model.getPassword(), BCrypt.gensalt(12)); // mã hóa
//        model.setPassword(hashed);
        if (model.getEmail() == null || model.getEmail().isEmpty()) {
            return null;
        }
        return userRepository.save(model);
    }

    @Override
    public void delete(Integer id) {
        userRepository.deleteById(id);
    }

    @Override
    public User update(User model) {
        User user = userRepository.findById(model.getId()).orElse(null);
        if (user != null) {
            user.setAddress(model.getAddress());
            user.setPhone(model.getPhone());
            user.setBirthday(model.getBirthday());
            user.setFullname(model.getFullname());
        }
        return userRepository.save(model);
    }

    @Override
    public User findById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }
}
