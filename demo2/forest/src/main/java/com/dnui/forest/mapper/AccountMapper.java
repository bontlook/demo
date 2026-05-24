package com.dnui.forest.mapper;

import com.dnui.forest.pojo.Account;
import org.apache.ibatis.annotations.Param;

public interface AccountMapper {
    Account findByUsername(@Param("username") String username);
    void insert(Account account);
}
