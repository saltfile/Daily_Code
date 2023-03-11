package com.example.k2demo;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

@Component
public class CommerContoller {
    @KafkaListener(topics = "mytopic")
    public void listen(ConsumerRecord<?, String> record) {
        String value = record.value();
        System.out.println(value);
        System.out.println(record);
    }
}
