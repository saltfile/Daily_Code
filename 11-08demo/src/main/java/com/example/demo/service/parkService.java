package com.example.demo.service;

import com.example.demo.entiry.park;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
//dao层接口
public interface parkService {
    public int Add(park Part);

    public int Del(String parkid);

    public int DelByObj(park Part);

    public ArrayList<park> AllTable();
}
