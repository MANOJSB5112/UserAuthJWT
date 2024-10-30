package com.example.userauthjwt.NewUserEventPackage;

import com.example.userauthjwt.ExternalLibrary.AwsSNS.Service.AwsSNSService;
import com.example.userauthjwt.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class AwsSNSProcessor implements NewUserEventProcessor{
    private AwsSNSService awsSNSService;


    @Autowired
    public AwsSNSProcessor(AwsSNSService awsSNSService)
    {
        this.awsSNSService=awsSNSService;
    }

    @Override
    public void notifyUserUponSignUp(User user) {


    }

    @Override
    public void createUserInInventoryService(User user) {

    }
}
