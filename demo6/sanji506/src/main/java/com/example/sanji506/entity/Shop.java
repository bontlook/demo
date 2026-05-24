package com.example.sanji506.entity;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class Shop {
    private Long id;
    private String shopName;
    private String shopCode;
    private String ownerName;
    private String phone;
    private String address;
    private String description;
    private Integer status;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
