package com.example.sanji506.mapper;

import com.example.sanji506.entity.Inventory;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface InventoryMapper {
    @Select("SELECT i.*, p.product_name, p.warning_quantity FROM inventory i " +
            "LEFT JOIN product p ON i.product_id=p.id")
    List<Inventory> findAll();

    @Select("SELECT i.*, p.product_name, p.warning_quantity FROM inventory i " +
            "LEFT JOIN product p ON i.product_id=p.id " +
            "WHERE i.quantity < p.warning_quantity")
    List<Inventory> findWarningList();

    @Select("SELECT i.*, p.product_name, p.warning_quantity FROM inventory i " +
            "LEFT JOIN product p ON i.product_id=p.id " +
            "WHERE i.id=#{id}")
    Inventory findById(Long id);

    @Select("SELECT i.*, p.product_name, p.warning_quantity FROM inventory i " +
            "LEFT JOIN product p ON i.product_id=p.id " +
            "WHERE i.product_id=#{productId}")
    Inventory findByProductId(Long productId);

    @Insert("INSERT INTO inventory(product_id, warehouse_id, quantity, create_time, update_time) " +
            "VALUES(#{productId}, NULL, #{quantity}, NOW(), NOW())")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Inventory inventory);

    @Update("<script>" +
            "UPDATE inventory " +
            "<set>" +
            "  <if test='productId != null'>product_id=#{productId},</if>" +
            "  <if test='quantity != null'>quantity=#{quantity},</if>" +
            "  update_time=NOW()" +
            "</set>" +
            "WHERE id=#{id}" +
            "</script>")
    int update(Inventory inventory);

    @Delete("DELETE FROM inventory WHERE id=#{id}")
    int deleteById(Long id);
}

