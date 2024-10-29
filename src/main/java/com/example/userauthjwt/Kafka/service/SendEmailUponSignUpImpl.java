package com.example.userauthjwt.Kafka.service;

import com.example.userauthjwt.Kafka.dtos.SendEmailDto;
import com.example.userauthjwt.models.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class SendEmailUponSignUpImpl implements SendEmailUponSignUp{
    private KafkaTemplate<String,String> kafkaTemplate;
    private ObjectMapper objectMapper;

    public SendEmailUponSignUpImpl(KafkaTemplate<String,String> kafkaTemplate,ObjectMapper objectMapper){
        this.kafkaTemplate=kafkaTemplate;
        this.objectMapper=objectMapper;
    }
    @Override
    public void sendEmailNotification(User user) throws JsonProcessingException {
          SendEmailDto sendEmailDto=new SendEmailDto();
          sendEmailDto.setTo(user.getEmail());
          sendEmailDto.setFrom("manojsb5112@gmail.com");
          sendEmailDto.setSubject(" Welcome to ZStore ");
          sendEmailDto.setBody("""
          Thanks for Signing up !
          Hope you will have a great shopping experience ! - Team ZStore
          """);

          kafkaTemplate.send("sendEmail", objectMapper.writeValueAsString(sendEmailDto));

    }
}
