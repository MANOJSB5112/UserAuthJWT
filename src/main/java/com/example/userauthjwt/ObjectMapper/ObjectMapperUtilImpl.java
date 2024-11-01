package com.example.userauthjwt.ObjectMapper;

import com.example.userauthjwt.dtos.NewUserExternalDto;
import com.example.userauthjwt.models.RoleType;
import com.example.userauthjwt.models.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ObjectMapperUtilImpl implements ObjectMapperUtil{
    private ObjectMapper objectMapper;
    private Utility utility;

    @Autowired
    public ObjectMapperUtilImpl(ObjectMapper objectMapper,Utility utility){
        this.objectMapper=objectMapper;
        this.utility=utility;
    }

    @Override
    public String getNewUserObjectForRoleType(User user, RoleType roleType) throws JsonProcessingException {
        NewUserExternalDto newUserExternalDto=new NewUserExternalDto();
        newUserExternalDto.setUserId(user.getId());
        newUserExternalDto.setName(user.getName());
        newUserExternalDto.setEmail(user.getEmail());
        newUserExternalDto.setPhoneNumber(user.getPhoneNumber());
        String roleName=utility.getRoleNameForRoleType(roleType);
        newUserExternalDto.setRoleName(roleName);
        return objectMapper.writeValueAsString(newUserExternalDto);
    }
}
