package com.example.userauthjwt.NewUserEventPackage;

import com.example.userauthjwt.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ActionsUponUserSignUpImpl implements ActionsUponUserSignUp{
    private NewUserEventProcessor newUserEventProcessor;

    @Autowired
    public ActionsUponUserSignUpImpl(NewUserEventProcessor newUserEventProcessor)
    {
        this.newUserEventProcessor=newUserEventProcessor;
    }
    @Override
    public void notifyUserUponSignUp(User user) {

    }

    @Override
    public void createUserInInventoryService(User user) {

    }
}
