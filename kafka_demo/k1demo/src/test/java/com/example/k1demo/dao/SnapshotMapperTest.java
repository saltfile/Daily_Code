package com.example.k1demo.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class SnapshotMapperTest {
    @Autowired
    SnapshotMapper snapshotMapper;

    @Test
    void add() {
        snapshotMapper.Add("xxx","xxxx",50);
    }
}