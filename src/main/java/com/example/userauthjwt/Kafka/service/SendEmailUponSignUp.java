package com.example.userauthjwt.Kafka.service;

import com.example.userauthjwt.models.User;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface SendEmailUponSignUp {
    void sendEmailNotification(User user) throws JsonProcessingException;
}
