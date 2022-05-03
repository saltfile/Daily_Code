package com.example.demo.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MyBatisUserDao {
    /**
     * TODO:在user_Tab中添加一个用户信息
     * @return 如果受影响行数等于1就说明是正确的
     */
    @Insert("insert into user_Tab(id,password,username,tel,emil,address,gender,dentcard,jurs,age)values (#{id},#{password},#{username},#{tel},#{emil},#{address},#{gender},#{dentcard},#{jurs},#{age})")
    int Add(@Param("id")String id, @Param("password")String password, @Param("username")String username, @Param("tel")String tel, @Param("emil")String emil, @Param("address")String address, @Param("gender")String gender,@Param("dentcard")String dentcard,@Param("jurs")int jurs,@Param("age")int age);

}
