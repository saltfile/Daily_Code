package org.example;

import org.apache.hadoop.conf.Configuration;

public class HbaseClient {
    static Configuration config = null;

    static {
        config = HBaseConfiguration.create();
        config.set("hbase.zookeeper.quorum", "slave1,slave2");
        config.set("hbase.zookeeper.property.clientPort", "2181");
    }





}
