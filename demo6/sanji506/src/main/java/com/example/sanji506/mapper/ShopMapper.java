package com.example.sanji506.mapper;

import com.example.sanji506.entity.Shop;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface ShopMapper {
    
    @Select("SELECT * FROM shop WHERE id=#{id}")
    Shop findById(Long id);
    
    @Select("SELECT * FROM shop")
    List<Shop> findAll();

    @Select("SELECT * FROM shop WHERE shop_code=#{shopCode}")
    Shop findByShopCode(String shopCode);

    @Select("SELECT COUNT(*) FROM shop")
    int count();

    @Insert("INSERT INTO shop(shop_name, shop_code, owner_name, phone, address, description, status, create_time, update_time) " +
            "VALUES(#{shopName}, #{shopCode}, #{ownerName}, #{phone}, #{address}, #{description}, #{status}, NOW(), NOW())")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Shop shop);

    @Update("<script>" +
            "UPDATE shop " +
            "<set>" +
            "  <if test='shopName != null'>shop_name=#{shopName},</if>" +
            "  <if test='shopCode != null'>shop_code=#{shopCode},</if>" +
            "  <if test='ownerName != null'>owner_name=#{ownerName},</if>" +
            "  <if test='phone != null'>phone=#{phone},</if>" +
            "  <if test='address != null'>address=#{address},</if>" +
            "  <if test='description != null'>description=#{description},</if>" +
            "  <if test='status != null'>status=#{status},</if>" +
            "  update_time=NOW()" +
            "</set>" +
            "WHERE id=#{id}" +
            "</script>")
    int update(Shop shop);

    @Delete("DELETE FROM shop WHERE id=#{id}")
    int deleteById(Long id);
}
