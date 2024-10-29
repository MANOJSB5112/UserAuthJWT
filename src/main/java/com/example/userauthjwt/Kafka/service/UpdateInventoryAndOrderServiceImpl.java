package com.example.userauthjwt.Kafka.service;

import com.example.userauthjwt.Kafka.dtos.UpdateNewUserDto;
import com.example.userauthjwt.models.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class UpdateInventoryAndOrderServiceImpl implements UpdateInventoryAndOrderService{
    private KafkaTemplate<String,String> kafkaTemplate;
    private ObjectMapper objectMapper;

    @Autowired
    public UpdateInventoryAndOrderServiceImpl(KafkaTemplate<String,String> kafkaTemplate,ObjectMapper objectMapper)
    {
        this.kafkaTemplate=kafkaTemplate;
        this.objectMapper=objectMapper;
    }
    @Override
    public void notifyInventoryAndOrderService(User user) throws JsonProcessingException {
        UpdateNewUserDto updateNewUserDto=new UpdateNewUserDto();
        updateNewUserDto.setUserId(user.getId());
        updateNewUserDto.setName(user.getName());
        updateNewUserDto.setEmail(user.getEmail());
        updateNewUserDto.setPhoneNumber(user.getPhoneNumber());
        updateNewUserDto.setRoleName("Customer");

        kafkaTemplate.send("createNewUser", objectMapper.writeValueAsString(updateNewUserDto));
    }
}

//(topics= "createNewUser", groupId = "inventoryAndOrderService")