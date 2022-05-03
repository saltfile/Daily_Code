package com.example.demo.dao;

import com.example.demo.entiry.user_Tab;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * 展示正常的jdbc使用方式，算是比较落后的方式
 */
@Slf4j
@Repository
public class userDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * TODO:在user_Tab中添加一个用户信息
     * @param user 要添加进去的表信息
     * @return 如果受影响行数等于1就说明是正确的
     */
    public int Add(user_Tab user){
        //sql语句
        String sql = "INSERT INTO user_Tab(id,password,username,tel,emil,address,gender,dentcard,jurs,age) VALUES (?,?,?,?,?,?,?,?,?,?)";
        //这些user_Tab属性用在jdbc操作的不定参上

        //jdbc添加调用,如果调用成功会返回所受影响的行数
        return jdbcTemplate.update(sql,
                user.getId(),user.getPassword(),
                user.getUsername(),user.getTel(),
                user.getEmil(),user.getAddress(),
                user.getGender(),user.getDentcard(),
                user.getJurs(),user.getAge()
                );
    }

    /**
     * TODO:删除单个用户时输入账号
     * @param id 用户的id
     * @return 受影响行数判断是否被删除
     */
    public int Del(String id){
        String sql = "DELETE FROM user_Tab WHERE id = ?";
        //执行数据库删除操作
        return jdbcTemplate.update(sql,id);
    }


    /**
     * TODO:用户修改个人信息
     * @param user 用户修改后
     * @return 受影响行数
     */
    public int Update(user_Tab user){
        String sql = "update user_Tab set username =?,emil=?,address=?,gender=?,dentcard=?,age=? where id=?";
        int res = jdbcTemplate.update(sql, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setString(1,user.getUsername());
                preparedStatement.setString(2,user.getEmil());
                preparedStatement.setString(3,user.getAddress());
                preparedStatement.setString(4,user.getGender());
                preparedStatement.setString(5,user.getDentcard());
                preparedStatement.setInt(6,user.getAge());
                preparedStatement.setString(7,user.getId());
            }
        });
        return res;

    }

    /**
     * TODO:通过账号密码查找用户
     * @param id 账号
     * @param password 密码
     * @return 所有的用户信息
     */
    public user_Tab Sel(String id,String password){
        String sql = "SELECT * FROM user_Tab WHERE id = ? AND password = ?";
        user_Tab user = new user_Tab();
        jdbcTemplate.query(sql, new Object[]{id, password}, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet resultSet) throws SQLException {
                user.setId(resultSet.getString(1));
                user.setPassword(resultSet.getString(2));
                user.setUsername(resultSet.getString(3));
                user.setTel(resultSet.getString(4));
                user.setEmil(resultSet.getString(5));
                user.setAddress(resultSet.getString(6));
                user.setGender(resultSet.getString(7));
                user.setDentcard(resultSet.getString(8));
                user.setJurs(resultSet.getInt(9));
                user.setAge(resultSet.getInt(10));
            }
        });
        return user;

    }
    /**
     * TODO:查看整个用户表信息
     * @return 所有用户的结果集
     */
    public ArrayList<user_Tab> ShowAll(){
        //最终的返回值
        ArrayList<user_Tab> res = new ArrayList<>();
        //sql语句
        String sql = "SELECT * FROM user_Tab";
        //执行sql语句
        jdbcTemplate.query(sql, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet resultSet) throws SQLException {
                int lenset = resultSet.getRow();
                log.info("表中一共有： "+lenset+"  条数据");
                user_Tab p = new user_Tab();
                //将表中值放进p中
                p.setId(resultSet.getString(1));
                p.setPassword(resultSet.getString(2));
                p.setUsername(resultSet.getString(3));
                p.setTel(resultSet.getString(4));
                p.setEmil(resultSet.getString(5));
                p.setAddress(resultSet.getString(6));
                p.setGender(resultSet.getString(7));
                p.setDentcard(resultSet.getString(8));
                p.setJurs(resultSet.getInt(9));
                p.setAge(resultSet.getInt(10));
                //将p添加hat Set size is到结果集合中
                res.add(p);
            }
        });
        return res;
    }


}
