package com.example.userauthjwt.Kafka.service;

import com.example.userauthjwt.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KafkaServiceImpl implements KafkaService{
    private SendEmailUponSignUp sendEmailUponSignUp;

    @Autowired
    public KafkaServiceImpl(SendEmailUponSignUp sendEmailUponSignUp)
    {
        this.sendEmailUponSignUp=sendEmailUponSignUp;
    }
    @Override
    public void actionsUponSignUp(User user) {

    }
}
