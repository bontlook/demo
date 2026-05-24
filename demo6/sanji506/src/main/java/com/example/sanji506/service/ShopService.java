package com.example.sanji506.service;

import com.example.sanji506.entity.Shop;
import java.util.List;

public interface ShopService {
    List<Shop> findAll();
    Shop findById(Long id);
    Shop findByShopCode(String shopCode);
    int insert(Shop shop);
    int update(Shop shop);
    int deleteById(Long id);
    int count();
}
