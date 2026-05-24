package com.example.sanji506.entity;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class Inventory {
    private Long id;
    private Long productId;
    private String productName;
    private Integer quantity;
    private Integer warningQuantity;
    private Long warehouseId;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
