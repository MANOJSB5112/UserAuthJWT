package com.example.userauthjwt.ObjectMapper;

import com.example.userauthjwt.dtos.SendEmailDto;
import com.example.userauthjwt.dtos.UpdateNewUserDto;
import com.example.userauthjwt.models.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

@Component
public class ObjectMapperUtilImpl implements ObjectMapperUtil{
    private ObjectMapper objectMapper;

    public ObjectMapperUtilImpl(ObjectMapper objectMapper){
        this.objectMapper=objectMapper;
    }

    @Override
    public String getNewUserNotificationObject(User user) throws JsonProcessingException {
        SendEmailDto sendEmailDto=new SendEmailDto();
        sendEmailDto.setName(user.getName());
        sendEmailDto.setEmail(user.getEmail());
        return objectMapper.writeValueAsString(sendEmailDto);
    }

    @Override
    public String getNewUserCreationObject(User user) throws JsonProcessingException {
        UpdateNewUserDto updateNewUserDto=new UpdateNewUserDto();
        updateNewUserDto.setUserId(user.getId());
        updateNewUserDto.setName(user.getName());
        updateNewUserDto.setEmail(user.getEmail());
        updateNewUserDto.setPhoneNumber(user.getPhoneNumber());
        updateNewUserDto.setRoleName("Customer");

        return objectMapper.writeValueAsString(updateNewUserDto);
    }
}
