package org.saltfish.example.Server.service;

import io.minio.MinioClient;
import io.minio.UploadObjectArgs;

public class imgService {
   private  static MinioClient client;
    static {
       client =  MinioClient.builder()
                .endpoint("http://192.168.1.7:9000")
                .credentials("admin", "12345678")
                .build();
        System.out.println("客户端链接成功");
    }


}
