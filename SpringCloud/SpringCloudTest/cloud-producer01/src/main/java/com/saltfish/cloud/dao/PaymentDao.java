package com.saltfish.cloud.dao;

import com.saltfish.cloud.entities.payment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface PaymentDao {
    @Insert("INSERT INTO payment(numbers,content)VALUES (#{numbers},#{content});")
    int Add(@Param("numbers")String numbers, @Param("content")String content);
    @Select("SELECT * FROM payment where id = #{id}")
    payment Sel(@Param("id")Long id);
}
