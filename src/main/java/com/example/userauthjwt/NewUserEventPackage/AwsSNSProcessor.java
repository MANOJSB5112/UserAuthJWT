package com.example.userauthjwt.NewUserEventPackage;

import com.example.userauthjwt.ExternalLibrary.AwsSNS.Service.AwsSNSService;
import com.example.userauthjwt.ObjectMapper.ObjectMapperUtil;
import com.example.userauthjwt.models.RoleType;
import com.example.userauthjwt.models.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class AwsSNSProcessor implements UserSignUpTopicProcessor{
    private AwsSNSService awsSNSService;
    private ObjectMapperUtil objectMapperUtil;


    @Autowired
    public AwsSNSProcessor(AwsSNSService awsSNSService,ObjectMapperUtil objectMapperUtil)
    {
        this.awsSNSService=awsSNSService;
        this.objectMapperUtil=objectMapperUtil;
    }

    @Override
    public void notifyUserSignUpTopic(User user, RoleType roleType) throws JsonProcessingException {
        String newUserObject=objectMapperUtil.getNewUserObjectForRoleType(user,roleType);
        awsSNSService.publishUserSignUpTopic(newUserObject);

    }
}
