package com.dnui.forest.service;

import com.dnui.forest.pojo.User;

import java.util.List;

public interface UserService {
    void deleteById(int id);
    List<User> selectAll();
    void insert(User user);
    void update(User user);
    User findByName(String name);

}
