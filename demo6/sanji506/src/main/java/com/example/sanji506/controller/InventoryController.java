package com.example.sanji506.controller;

import com.example.sanji506.entity.Inventory;
import com.example.sanji506.entity.Product;
import com.example.sanji506.service.InventoryService;
import com.example.sanji506.service.impl.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class InventoryController {
    @Autowired
    private InventoryService inventoryService;
    
    @Autowired
    private ProductService productService;

    @GetMapping("/inventory/list")
    public String list(Model model) {
        List<Inventory> list = inventoryService.findAll();
        model.addAttribute("list", list);
        return "inventory/list";
    }

    @GetMapping("/inventory/warning")
    public String warning(Model model) {
        List<Inventory> warningList = inventoryService.findWarning();
        model.addAttribute("warningList", warningList);
        return "inventory/warning";
    }

    @GetMapping("/inventory/chart")
    public String chart(Model model) {
        List<Inventory> list = inventoryService.findAll();
        model.addAttribute("inventoryData", list);
        return "inventory/chart";
    }

    @GetMapping("/inventory/chartData")
    @ResponseBody
    public List<Map<String, Object>> getChartData() {
        List<Inventory> list = inventoryService.findAll();
        return list.stream().map(item -> {
            Map<String, Object> map = new HashMap<>();
            map.put("name", item.getProductName() != null ? item.getProductName() : "未知商品");
            map.put("value", item.getQuantity());
            map.put("productId", item.getProductId());
            return map;
        }).collect(Collectors.toList());
    }

    @PostMapping("/inventory/add")
    @ResponseBody
    public String addInventory(@RequestBody Inventory inventory) {
        try {
            inventoryService.insert(inventory);
            return "success";
        } catch (Exception e) {
            return "error: " + e.getMessage();
        }
    }

    @PostMapping("/inventory/add-with-product")
    @ResponseBody
    public String addInventoryWithProduct(@RequestBody Map<String, Object> requestData) {
        try {
            Map<String, Object> productData = (Map<String, Object>) requestData.get("product");
            Integer quantity = Integer.parseInt(requestData.get("quantity").toString());
            
            Product product = new Product();
            product.setProductName((String) productData.get("productName"));
            
            String productCode = (String) productData.get("productCode");
            if (productCode == null || productCode.trim().isEmpty()) {
                productCode = "P" + System.currentTimeMillis() % 1000000;
            }
            product.setProductCode(productCode);
            
            product.setWarningQuantity(Integer.parseInt(productData.get("warningQuantity").toString()));
            
            productService.insert(product);
            
            Inventory inventory = new Inventory();
            inventory.setProductId(product.getId());
            inventory.setQuantity(quantity);
            inventoryService.insert(inventory);
            
            return "success";
        } catch (Exception e) {
            return "error: " + e.getMessage();
        }
    }

    @PostMapping("/inventory/update")
    @ResponseBody
    public String updateInventory(@RequestBody Inventory inventory) {
        try {
            inventoryService.update(inventory);
            return "success";
        } catch (Exception e) {
            return "error: " + e.getMessage();
        }
    }

    @PostMapping("/inventory/delete/{id}")
    @ResponseBody
    public String deleteInventory(@PathVariable Long id) {
        try {
            inventoryService.deleteById(id);
            return "success";
        } catch (Exception e) {
            return "error: " + e.getMessage();
        }
    }
}
