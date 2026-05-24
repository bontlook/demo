package com.example.sanji506.service.impl;

import com.example.sanji506.entity.Inventory;
import com.example.sanji506.mapper.InventoryMapper;
import com.example.sanji506.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryServiceImpl implements InventoryService {

    @Autowired
    private InventoryMapper inventoryMapper;

    @Override
    public List<Inventory> findAll() {
        return inventoryMapper.findAll();
    }

    @Override
    public List<Inventory> findWarning() {
        return inventoryMapper.findWarningList();
    }

    @Override
    public Inventory findById(Long id) {
        return inventoryMapper.findById(id);
    }

    @Override
    public Inventory findByProductId(Long productId) {
        return inventoryMapper.findByProductId(productId);
    }

    @Override
    public int insert(Inventory inventory) {
        return inventoryMapper.insert(inventory);
    }

    @Override
    public int update(Inventory inventory) {
        return inventoryMapper.update(inventory);
    }

    @Override
    public int deleteById(Long id) {
        return inventoryMapper.deleteById(id);
    }
}
