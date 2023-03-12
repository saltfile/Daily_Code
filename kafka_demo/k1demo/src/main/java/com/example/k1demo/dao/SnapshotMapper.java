package com.example.k1demo.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SnapshotMapper {


    @Insert("insert into (timep,uname,gname,num)values (NOW(),#{})")
    int Add(@Param("id")Integer id, @Param("name")String name );




}
