package com.example.userauthjwt.ExternalLibrary.Kafka.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaServiceImpl implements KafkaService{
    private KafkaTemplate<String,String> kafkaTemplate;
    @Autowired
    public KafkaServiceImpl(KafkaTemplate<String,String> kafkaTemplate){
        this.kafkaTemplate=kafkaTemplate;
    }

    @Override
    public void publishUserSignUpTopic(String newUserObject) throws JsonProcessingException {
        kafkaTemplate.send("user-signup-event", newUserObject);
    }
}
