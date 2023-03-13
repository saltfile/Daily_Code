package com.example.k1demo.dao;

import com.sun.org.glassfish.gmbal.ManagedObject;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface OtherMapper {
    @Insert("insert into other(uid,menoy)values (#{uid},#{menoy})")
    int Add(@Param("uid")int uid,@Param("menoy")int menoy);
}
