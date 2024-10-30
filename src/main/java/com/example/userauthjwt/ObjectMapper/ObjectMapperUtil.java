package com.example.userauthjwt.ObjectMapper;

import com.example.userauthjwt.models.User;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface ObjectMapperUtil {
    String getNewUserNotificationObject(User user) throws JsonProcessingException;
    String getNewUserCreationObject(User user) throws JsonProcessingException;
}
