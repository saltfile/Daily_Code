package com.example.demo.dao;
import com.example.demo.enitys.user;
import com.example.demo.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

@Repository
public class userDao implements userService {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public int register(user u) {
        String sql = "insert into system.user(id,name,password)values (?,?,?)";

        return jdbcTemplate.update(sql,u.id,u.name,u.password);
    }

    @Override
    public user login(String id, String password) {
        String sql = "SELECT * FROM system.user WHERE id = ? AND password = ?";
        user u = new user();
        jdbcTemplate.query(sql, new Object[]{id, password}, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet rs) throws SQLException {
                u.setId(rs.getString(1));
                u.setPassword(rs.getString(3));
                u.setName(rs.getString(2));
            }
        });
        return u;
    }

    @Override
    public int del(String id, String password) {
        String sql = "DELETE FROM system.user WHERE id = ? AND password = ?";
        int num = jdbcTemplate.update(sql,id,password);
        if(num > 0){
            String sql1 = "insert into system.trashcan(id,time)values (?,?)";
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String time = df.format(new Date());
            Timestamp ts = Timestamp.valueOf(time);
            return jdbcTemplate.update(sql1,id,ts);
        }
        return -2;
    }


}
