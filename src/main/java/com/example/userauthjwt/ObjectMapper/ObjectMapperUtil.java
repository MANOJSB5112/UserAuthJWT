package com.example.userauthjwt.ObjectMapper;

import com.example.userauthjwt.models.RoleType;
import com.example.userauthjwt.models.User;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface ObjectMapperUtil {
    String getNewUserObjectForRoleType(User user, RoleType roleType) throws JsonProcessingException;
}
