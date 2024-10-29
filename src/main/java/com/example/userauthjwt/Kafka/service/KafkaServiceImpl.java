package com.example.userauthjwt.Kafka.service;

import com.example.userauthjwt.models.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KafkaServiceImpl implements KafkaService{
    private SendEmailUponSignUp sendEmailUponSignUp;
    private UpdateInventoryAndOrderService updateInventoryAndOrderService;

    @Autowired
    public KafkaServiceImpl(SendEmailUponSignUp sendEmailUponSignUp,UpdateInventoryAndOrderService updateInventoryAndOrderService)
    {
        this.sendEmailUponSignUp=sendEmailUponSignUp;
        this.updateInventoryAndOrderService=updateInventoryAndOrderService;
    }
    @Override
    public void actionsUponSignUp(User user) throws JsonProcessingException {
        sendEmailUponSignUp.sendEmailNotification(user);
        updateInventoryAndOrderService.notifyInventoryAndOrderService(user);
    }
}
