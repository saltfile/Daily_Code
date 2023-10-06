package org.example.contoller;

import org.example.config.DirectExchangeConfig;
import org.example.config.TopicExchangeConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/direct")
public class DirectContoller {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("send")
    public Object sendMsg(@RequestParam(value = "route") String route, @RequestParam(value = "msg")String msg) {
        rabbitTemplate.convertAndSend(DirectExchangeConfig.DIRECT_EXCHANGE,DirectExchangeConfig.DIRECT_ROUTING_KEY , msg);
        return "topic消息发送成功！！";
    }
}
