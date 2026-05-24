package com.example.sanji506.service;

import com.example.sanji506.entity.User;

import java.util.List;

public interface UserService {
    User findByUsername(String username);

    List<User> findAll();

    User findById(Long id);

    int insert(User user);

    int update(User user);

    int deleteById(Long id);
}

