package com.example.userauthjwt.ExternalLibrary.Kafka.service;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface KafkaService {
    void publishUserSignUpTopic(String newUserObject) throws JsonProcessingException;
}
