package com.saltfish.expamle.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AdminDao {

    @Insert("insert into admin(id,name)values (#{id},#{name})")
    int Add(@Param("id")String id,@Param("name")String name);






}
