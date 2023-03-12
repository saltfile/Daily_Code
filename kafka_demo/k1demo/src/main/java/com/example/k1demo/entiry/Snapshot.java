package com.example.k1demo.entiry;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class Snapshot {
    public Timestamp timep;
    public String uname;
    public String gname;
    public int num;
}
