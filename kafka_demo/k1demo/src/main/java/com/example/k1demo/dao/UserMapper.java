package com.example.k1demo.dao;

import com.example.k1demo.entiry.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Insert("insert into user(accout)values (#{accout})")
    int Add(@Param("accout")String accout );


    @Select("SELECT * FROM user WHERE id =#{id}")
    User find(@Param("id") int id);
}
