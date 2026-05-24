package com.example.sanji506.service.impl;

import com.example.sanji506.entity.Shop;
import com.example.sanji506.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ShopController {
    
    @Autowired
    private ShopService shopService;

    @GetMapping("/shops")
    public String shops(Model model) {
        List<Shop> shops = shopService.findAll();
        model.addAttribute("shops", shops);
        return "shops";
    }

    @GetMapping("/shop/info/{id}")
    @ResponseBody
    public Shop getShopInfo(@PathVariable Long id) {
        return shopService.findById(id);
    }

    @PostMapping("/shop/add")
    @ResponseBody
    public String addShop(@RequestBody Shop shop) {
        try {
            shop.setStatus(1);
            shopService.insert(shop);
            return "success";
        } catch (Exception e) {
            return "error: " + e.getMessage();
        }
    }

    @PostMapping("/shop/update")
    @ResponseBody
    public String updateShop(@RequestBody Shop shop) {
        try {
            shopService.update(shop);
            return "success";
        } catch (Exception e) {
            return "error: " + e.getMessage();
        }
    }

    @PostMapping("/shop/delete/{id}")
    @ResponseBody
    public String deleteShop(@PathVariable Long id) {
        try {
            shopService.deleteById(id);
            return "success";
        } catch (Exception e) {
            return "error: " + e.getMessage();
        }
    }

    @GetMapping("/shop/count")
    @ResponseBody
    public int getShopCount() {
        return shopService.count();
    }
}
