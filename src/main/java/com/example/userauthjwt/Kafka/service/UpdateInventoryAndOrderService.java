package com.example.userauthjwt.Kafka.service;

import com.example.userauthjwt.models.User;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface UpdateInventoryAndOrderService {
    void notifyInventoryAndOrderService(User user) throws JsonProcessingException;
}
