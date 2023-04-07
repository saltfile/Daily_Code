package com.example.work_test.pojo;

import lombok.Data;
import org.bson.types.ObjectId;

public class test {
    public Integer id;
    public String name;
    public test() {
    }
    public test(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
