package com.example.sanji506.service;

import com.example.sanji506.entity.Inventory;
import java.util.List;

public interface InventoryService {
    List<Inventory> findAll();
    List<Inventory> findWarning();
    Inventory findById(Long id);
    Inventory findByProductId(Long productId);
    int insert(Inventory inventory);
    int update(Inventory inventory);
    int deleteById(Long id);
}


