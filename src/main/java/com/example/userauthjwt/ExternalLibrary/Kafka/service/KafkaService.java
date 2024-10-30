package com.example.userauthjwt.ExternalLibrary.Kafka.service;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface KafkaService {
    void notifyUserUponSignUp(String emailObject) throws JsonProcessingException;
    void createUserInInventoryService(String userCreationObject) throws JsonProcessingException;
}
