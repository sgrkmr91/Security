package com.example.security.config;

import com.example.security.model.UserProducer;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

@Configuration
public class KafkaProducerConfiguration {

    @Bean
    public KafkaTemplate<String,String> kafkaTemplate(ProducerFactory<String, String> producerFactory){
        return new KafkaTemplate<>(producerFactory);
    }

    @Bean
    @Qualifier("kafkaTemplate")
    public KafkaTemplate<String, UserProducer> kafkaTemplateForProducer(ProducerFactory<String,UserProducer> producerFactory){
        return new KafkaTemplate<>(producerFactory);
    }
}
