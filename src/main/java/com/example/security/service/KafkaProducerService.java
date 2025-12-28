package com.example.security.service;

import com.example.security.model.UserProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class KafkaProducerService {

    private static final String topic = "testing-topic";

    @Autowired
    @Qualifier("kafkaTemplate")
    private KafkaTemplate<String,UserProducer> kafkaTemplate;

    public void publishMessage(String message){
        UserProducer userProducer = new UserProducer();
        userProducer.setPId(UUID.randomUUID().toString());
        userProducer.setMessage(message);
        kafkaTemplate.send(topic,userProducer);
    }


}
