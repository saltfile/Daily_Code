package org.example.listener;

import org.example.config.DirectExchangeConfig;
import org.example.config.TopicExchangeConfig;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;


@Service
public class MessageListener {



    @RabbitHandler
    @RabbitListener(queues = TopicExchangeConfig.TOPIC_QUEUE)
    public void MessageHandler(String msg){
        System.out.println(msg);
    }



    @RabbitHandler
    @RabbitListener(queues = DirectExchangeConfig.DIRECT_QUEUE)
    public void DirectHandler1(String msg){
        System.out.println("direct1 "+msg);
    }

    @RabbitHandler
    @RabbitListener(queues = DirectExchangeConfig.DIRECT_QUEUE2)
    public void DirectHandler2(String msg){
        System.out.println("direct2"+msg);
    }







}
