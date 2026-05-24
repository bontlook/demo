package com.example.sanji506.entity;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class Product {
    private Long id;
    private String productCode;
    private String productName;
    private Integer warningQuantity;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
