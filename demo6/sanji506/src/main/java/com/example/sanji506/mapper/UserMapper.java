package com.example.sanji506.mapper;

import com.example.sanji506.entity.User;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface UserMapper {
    @Select("SELECT * FROM user WHERE username=#{username}")
    User findByUsername(String username);

    @Select("SELECT * FROM user")
    List<User> findAll();

    @Select("SELECT * FROM user WHERE id=#{id}")
    User findById(Long id);

    @Insert("INSERT INTO user(username, password, nickname, phone, create_time, update_time) " +
            "VALUES(#{username}, #{password}, #{nickname}, #{phone}, NOW(), NOW())")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(User user);

    @Update("UPDATE user SET password=#{password}, nickname=#{nickname}, phone=#{phone}, " +
            "update_time=NOW() WHERE id=#{id}")
    int update(User user);

    @Delete("DELETE FROM user WHERE id=#{id}")
    int deleteById(Long id);
}
