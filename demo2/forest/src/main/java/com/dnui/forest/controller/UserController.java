package com.dnui.forest.controller;

import com.dnui.forest.common.Result;
import com.dnui.forest.pojo.User;
import com.dnui.forest.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @Value("${app.upload-dir}")
    private String uploadDir;

    @PostMapping("/insert")
    public Result<Void> insert(@RequestBody User user) {
        user.setPicture(uploadDir + user.getPicture());
        userService.insert(user);
        return Result.success();
    }

    @DeleteMapping("/delete")
    public Result<Void> delete(@RequestParam("id") int id) {
        userService.deleteById(id);
        return Result.success();
    }

    @PutMapping("/update")
    public Result<Void> update(@RequestBody User user) {
        userService.update(user);
        return Result.success();
    }

    @GetMapping("/select")
    public Result<List<User>> selectAll() {
        return Result.success(userService.selectAll());
    }

    @GetMapping("/find")
    public Result<User> findByName(@RequestParam("name") String username) {
        return Result.success(userService.findByName(username));
    }
}
