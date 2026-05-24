package com.example.sanji506;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.sanji506.mapper")
public class Sanji506Application {

    public static void main(String[] args) {

        SpringApplication.run(Sanji506Application.class, args);
    }

}
