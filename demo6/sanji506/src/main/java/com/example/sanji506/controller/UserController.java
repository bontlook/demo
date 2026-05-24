package com.example.sanji506.controller;

import com.example.sanji506.entity.User;
import com.example.sanji506.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {
    
    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public String users(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/user/info")
    @ResponseBody
    public User getUserInfo(@RequestParam String username) {
        return userService.findByUsername(username);
    }

    @GetMapping("/user/info/{id}")
    @ResponseBody
    public User getUserInfoById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @PostMapping("/user/add")
    @ResponseBody
    public String addUser(@RequestBody User user) {
        try {
            userService.insert(user);
            return "success";
        } catch (Exception e) {
            return "error: " + e.getMessage();
        }
    }

    @PostMapping("/user/update")
    @ResponseBody
    public String updateUser(@RequestBody User user) {
        try {
            userService.update(user);
            return "success";
        } catch (Exception e) {
            return "error: " + e.getMessage();
        }
    }

    @PostMapping("/user/delete/{id}")
    @ResponseBody
    public String deleteUser(@PathVariable Long id) {
        try {
            userService.deleteById(id);
            return "success";
        } catch (Exception e) {
            return "error: " + e.getMessage();
        }
    }
}
