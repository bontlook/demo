package com.example.sanji506.controller;

import com.example.sanji506.entity.Product;
import com.example.sanji506.service.impl.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ProductController {
    
    @Autowired
    private ProductService productService;

    @GetMapping("/product/list")
    @ResponseBody
    public List<Product> list() {
        return productService.findAll();
    }

    @PostMapping("/product/add")
    @ResponseBody
    public String addProduct(@RequestBody Product product) {
        try {
            productService.insert(product);
            return "success";
        } catch (Exception e) {
            return "error: " + e.getMessage();
        }
    }

    @PostMapping("/product/update")
    @ResponseBody
    public String updateProduct(@RequestBody Product product) {
        try {
            productService.update(product);
            return "success";
        } catch (Exception e) {
            return "error: " + e.getMessage();
        }
    }

    @PostMapping("/product/delete/{id}")
    @ResponseBody
    public String deleteProduct(@PathVariable Long id) {
        try {
            productService.deleteById(id);
            return "success";
        } catch (Exception e) {
            return "error: " + e.getMessage();
        }
    }
}
