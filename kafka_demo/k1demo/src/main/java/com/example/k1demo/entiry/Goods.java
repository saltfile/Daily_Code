package com.example.k1demo.entiry;

import lombok.Data;
import org.apache.kafka.common.protocol.types.Field;

@Data
public class Goods {
    public String name;
    public int num;
}
