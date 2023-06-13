package com.example.service;


import com.example.model.User;

import java.util.List;

public interface UserService {
    List<User> getAll();

    User add(User dto);

    void delete(Integer id);

    User update(User dto);

    User findById(Integer id);

    User findByEmail(String email);
}
