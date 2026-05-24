package com.example.sanji506.service.impl;

import com.example.sanji506.entity.Product;
import java.util.List;

public interface ProductService {
    Product findById(Long id);
    List<Product> findAll();
    int insert(Product product);
    int update(Product product);
    int deleteById(Long id);
}
