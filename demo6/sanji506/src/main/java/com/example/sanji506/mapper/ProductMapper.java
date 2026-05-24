package com.example.sanji506.mapper;

import com.example.sanji506.entity.Product;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface ProductMapper {
    
    @Select("SELECT * FROM product WHERE id=#{id}")
    Product findById(Long id);
    
    @Select("SELECT * FROM product")
    List<Product> findAll();

    @Insert("INSERT INTO product(product_code, product_name, warning_quantity, create_time, update_time) " +
            "VALUES(#{productCode}, #{productName}, #{warningQuantity}, NOW(), NOW())")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Product product);

    @Update("<script>" +
            "UPDATE product " +
            "<set>" +
            "  <if test='productCode != null'>product_code=#{productCode},</if>" +
            "  <if test='productName != null'>product_name=#{productName},</if>" +
            "  <if test='warningQuantity != null'>warning_quantity=#{warningQuantity},</if>" +
            "  update_time=NOW()" +
            "</set>" +
            "WHERE id=#{id}" +
            "</script>")
    int update(Product product);

    @Delete("DELETE FROM product WHERE id=#{id}")
    int deleteById(Long id);
}
