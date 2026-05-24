package com.example.sanji506.service.impl;

import com.example.sanji506.entity.Shop;
import com.example.sanji506.mapper.ShopMapper;
import com.example.sanji506.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopServiceImpl implements ShopService {

    @Autowired
    private ShopMapper shopMapper;

    @Override
    public List<Shop> findAll() {
        return shopMapper.findAll();
    }

    @Override
    public Shop findById(Long id) {
        return shopMapper.findById(id);
    }

    @Override
    public Shop findByShopCode(String shopCode) {
        return shopMapper.findByShopCode(shopCode);
    }

    @Override
    public int insert(Shop shop) {
        return shopMapper.insert(shop);
    }

    @Override
    public int update(Shop shop) {
        return shopMapper.update(shop);
    }

    @Override
    public int deleteById(Long id) {
        return shopMapper.deleteById(id);
    }

    @Override
    public int count() {
        return shopMapper.count();
    }
}
