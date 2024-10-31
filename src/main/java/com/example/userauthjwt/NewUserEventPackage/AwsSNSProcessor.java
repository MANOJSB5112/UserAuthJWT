package com.example.userauthjwt.NewUserEventPackage;

import com.example.userauthjwt.ExternalLibrary.AwsSNS.Service.AwsSNSService;
import com.example.userauthjwt.ObjectMapper.ObjectMapperUtil;
import com.example.userauthjwt.models.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class AwsSNSProcessor implements NewUserEventProcessor{
    private AwsSNSService awsSNSService;
    private ObjectMapperUtil objectMapperUtil;


    @Autowired
    public AwsSNSProcessor(AwsSNSService awsSNSService,ObjectMapperUtil objectMapperUtil)
    {
        this.awsSNSService=awsSNSService;
        this.objectMapperUtil=objectMapperUtil;
    }

    @Override
    public void notifyUserUponSignUp(User user) throws JsonProcessingException {
        String emailObject=objectMapperUtil.getNewUserNotificationObject(user);
        awsSNSService.notifyUserUponSignUp(emailObject);

    }

    @Override
    public void createUserInInventoryService(User user) {

    }
}
