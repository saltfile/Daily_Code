package com.example.k1demo.dao;

import com.example.k1demo.entiry.Goods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface GoodsMapper {

    @Select("SELECT * FROM goods WHERE name =#{name}")
    Goods find(@Param("name") String name);



}
