package com.dnui.forest.service;

import com.dnui.forest.pojo.Account;

public interface AccountService {
    Account findByUsername(String username);
    void register(Account account);
}
