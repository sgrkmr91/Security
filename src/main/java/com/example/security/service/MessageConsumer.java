package com.example.security.service;

import com.example.security.model.UserProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MessageConsumer {

    @KafkaListener(topics = "order-topic", groupId = "order-group")
    public void consume(UserProducer message) {
        log.info("Topic message is {}",message);
        System.out.println("Consumed message: " + message);
    }
}

