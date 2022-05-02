package com.example.demo.dao;


import com.example.demo.entiry.contorller;
import com.example.demo.service.controllerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * TODO:DAO层业务
 * */
//注入
@Repository
public class contotllerDao implements controllerService {
    //ioc容器拿出jdbc
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * @param name：是账号
     * @param password:是密码
     * @return 看是不是找到数据库的contorller
     * */
    @Override
    public contorller FindByName_Pass(String name, String password) {
        //sql语言
        String sql = "SELECT * FROM contorller WHERE ctr_id = ? AND ctr_password = ?";
        contorller s = new contorller();
        //执行查找命令
        jdbcTemplate.query(sql, new Object[]{name, password}, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet resultSet) throws SQLException {
                s.setCtr_id(resultSet.getString(1));
                s.setCtr_password(resultSet.getString(2));
            }
        });
        return s;
    }
    /**
     * TODO：注册管理员
     * @param con ：被添加的类
     * @return 是否添加成功的返回值
     * */
    @Override
    public int Add(contorller con) {
        //sql语句
        String sql = "insert into contorller(ctr_id,ctr_password)values (?,?)";
        //调用查找命令
        return jdbcTemplate.update(sql,con.ctr_id,con.ctr_password);
    }


}
