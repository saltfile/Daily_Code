package org.example.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TopicExchangeConfig {
    public static final String TOPIC_QUEUE = "topicQueue";
    public static final String TOPIC_EXCHANGE = "topicExchange";
    public static final String TOPIC_ROUTING_KEY = "topic*";

    @Bean
    public Queue topicQueue() {
        return new Queue(TOPIC_QUEUE, true);
    }

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(TOPIC_EXCHANGE, true, false);
    }


    @Bean
    public Binding bindingTopicExchange(Queue topicQueue, TopicExchange topicExchange) {
        return BindingBuilder.bind(topicQueue).to(topicExchange).with("topic.#");
    }




}
