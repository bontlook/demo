package com.dnui.forest.service.impl;

import com.dnui.forest.mapper.AccountMapper;
import com.dnui.forest.pojo.Account;
import com.dnui.forest.service.AccountService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;

@Service
public class AccountServiceImpl implements AccountService {

    @Resource
    private AccountMapper accountMapper;

    @Override
    public Account findByUsername(String username) {
        return accountMapper.findByUsername(username);
    }

    @Override
    public void register(Account account) {
        account.setPassword(encryptPassword(account.getPassword()));
        accountMapper.insert(account);
    }

    public static String encryptPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes());
            return HexFormat.of().formatHex(hash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("加密失败", e);
        }
    }
}
