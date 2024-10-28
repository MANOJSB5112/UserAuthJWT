package com.example.userauthjwt.Kafka.service;

import com.example.userauthjwt.models.User;

public interface KafkaService {
    void actionsUponSignUp(User user);
}
