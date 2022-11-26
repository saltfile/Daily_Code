package org.example;

import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MinioConfig {
    @Value("${minio.url}")
    private String url;
    @Value("${minio.accessKey}")
    private String accessKey;
    @Value("${minio.secretKey}")
    private String secretKey;
    @Value("${minio.bucketName:default}")
    private String bucketName;

//    @Bean
//    public MinioClient minioClient() {
//        try {
//            MinioClient minioClient = new MinioClient(this.url, this.accessKey, this.secretKey);
//            if(!minioClient.bucketExists(bucketName)) {
//                minioClient.makeBucket(bucketName);
//                minioClient.setBucketPolicy(bucketName, "download");
//            }
//            return minioClient;
//        } catch (Exception e) {
//            logger.error("", e);
//        }
//        return null;
//    }

}
