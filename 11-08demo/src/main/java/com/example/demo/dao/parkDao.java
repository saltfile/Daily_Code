package com.example.demo.dao;

import com.example.demo.entiry.park;
import com.example.demo.service.parkService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
/**
 * TODO:DAO层业务
 * */
@Repository
public class parkDao implements parkService {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    /**
     * TODO:在park表中添加数据
     * @param Part:这是被添加的类
     * @return 是否被添加成功的返回值
     * */
    @Override
    public int Add(park Part) {
        //sql语句
        String sql = "INSERT INTO park(park_id,place," +
                "isfree,charge,latitude,longitude)" +
                "VALUES (?,?,?,?,?,?)";
        //这些park属性用在jdbc操作的不定参上
        String park_id = Part.getPark_id();
        String place = Part.getPlace();
        String isfree = Part.getIsfree();
        double charge = Part.getCharge();
        double latitude = Part.getLatitude();
        double longitude = Part.getLongitude();
        //jdbc添加调用
        return jdbcTemplate.update(sql,park_id,place,isfree,charge,latitude,longitude);
    }

    /**
     * TODO:通过aprk_id删除相应的park
     * @param parkid :表中与之匹配的类
     * @return 是否删除成功的返回值
     * */
    @Override
    public int Del(String parkid) {
        //sql语句
        String sql = "DELETE FROM park WHERE park_id = ?";
        //执行数据库删除操作
        return jdbcTemplate.update(sql,parkid);
    }
    /**
     * TODO:通过所有参数精确删除相应的park
     * @param Part:表中与之匹配的类
     * @return 是否删除成功的返回值
     * */
    @Override
    public int DelByObj(park Part) {
        ///sql语句
        String sql = "DELETE FROM park WHERE park_id=? AND place=?" +
                "AND isfree=? AND charge=? AND latitude=? AND longitude=?";
        //执行数据库删除操作
        return jdbcTemplate.update(sql,Part.getPark_id(),
                Part.getPlace(),Part.getIsfree(),Part.getCharge(),
                Part.getLatitude(),Part.getLongitude());
    }

    /**
     * TODO:将表中所有的数据整合出一个结果集
     * @return 表中所有的结果集
     * */
    @Override
    public ArrayList<park> AllTable() {
        //最终的返回值
        ArrayList<park> res = new ArrayList<>();
        //sql语句
        String sql = "SELECT * FROM park";
        //执行sql语句
        jdbcTemplate.query(sql, new RowCallbackHandler() {

            @Override
            public void processRow(ResultSet resultSet) throws SQLException {
                int lenset = resultSet.getRow();
//                System.out.println("That Set size is "+lenset);

                park p = new park();
                //将表中值放进p中
                p.setPark_id(resultSet.getString(1));
                p.setPlace(resultSet.getString(2));
                p.setIsfree(resultSet.getString(3));
                p.setCharge(resultSet.getDouble(4));
                p.setLatitude(resultSet.getDouble(5));
                p.setLongitude(resultSet.getDouble(6));
                //将p添加到结果集合中
                res.add(p);
            }
        });
        return res;
    }










}
