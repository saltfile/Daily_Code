package com.example.demo.service;

import com.example.demo.dao.parkDao;
import com.example.demo.entiry.park;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
/**
 * TODO:这里的是业务层，由于目前业务单一所以只是调用dao层而已
 * */
@Service
public class ParkServicelmpl {
    @Autowired
    private parkDao parkDao;
    //添加park
    public int InsertPark(park Part){
        return parkDao.Add(Part);
    }
    //删除aprk
    public int DeletePark(String park_id){
        return parkDao.Del(park_id);
    }
    //返回结果集park
    public ArrayList<park> AllTableDate(){return parkDao.AllTable();}
    //通过类删除表中的park
    public int DeleteParkByObj(park Part){ return parkDao.DelByObj(Part);}
}
