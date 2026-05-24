package com.dnui.forest.controller;

import com.dnui.forest.common.Result;
import com.dnui.forest.pojo.Account;
import com.dnui.forest.service.AccountService;
import com.dnui.forest.service.impl.AccountServiceImpl;
import com.dnui.forest.util.JwtUtil;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class LoginController {

    @Resource
    private AccountService accountService;

    @Resource
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public Result<Map<String, Object>> login(@RequestBody Account loginRequest) {
        if (loginRequest.getUsername() == null || loginRequest.getPassword() == null) {
            throw new IllegalArgumentException("用户名和密码不能为空");
        }

        Account account = accountService.findByUsername(loginRequest.getUsername());
        if (account == null) {
            return Result.error(401, "用户不存在");
        }

        String encryptedPassword = AccountServiceImpl.encryptPassword(loginRequest.getPassword());
        if (!encryptedPassword.equals(account.getPassword())) {
            return Result.error(401, "密码错误");
        }

        String token = jwtUtil.generateToken(account.getId(), account.getUsername());

        Map<String, Object> data = new HashMap<>();
        data.put("token", token);
        data.put("username", account.getUsername());
        return Result.success(data);
    }

    @PostMapping("/register")
    public Result<Void> register(@RequestBody Account account) {
        if (account.getUsername() == null || account.getPassword() == null) {
            throw new IllegalArgumentException("用户名和密码不能为空");
        }

        Account existing = accountService.findByUsername(account.getUsername());
        if (existing != null) {
            return Result.error(400, "用户名已存在");
        }

        accountService.register(account);
        return Result.success();
    }
}
