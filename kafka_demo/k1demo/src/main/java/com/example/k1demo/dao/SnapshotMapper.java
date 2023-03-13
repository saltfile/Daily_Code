package com.example.k1demo.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface SnapshotMapper {


    @Insert("insert into snapshot(timep,uname,gname,num)values (NOW(),#{uname},#{gname},#{num})")
    int Add(@Param("uname")String uname,@Param("gname")String gname,@Param("num")int num);








}
