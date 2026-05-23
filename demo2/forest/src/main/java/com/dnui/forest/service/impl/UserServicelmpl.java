package com.dnui.forest.service.impl;

import com.dnui.forest.mapper.UserMapper;
import com.dnui.forest.pojo.User;
import com.dnui.forest.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServicelmpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public void deleteById(int id) {
        userMapper.deleteById(id);
    }

    @Override
    public List<User> selectAll() {
        return userMapper.selectAll();
    }

    @Override
    public void insert(User user) {
        userMapper.insert(user);
    }

    @Override
    public void update(User user) {
        userMapper.update(user);
    }

    @Override
    public User findByName(String username) {
       return userMapper.findByName(username);
    }


}
