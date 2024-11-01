package com.example.userauthjwt.NewUserEventPackage;

import com.example.userauthjwt.models.RoleType;
import com.example.userauthjwt.models.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ActionsUponUserSignUpImpl implements ActionsUponUserSignUp{
    private UserSignUpTopicProcessor userSignUpTopicProcessor;

    @Autowired
    public ActionsUponUserSignUpImpl(UserSignUpTopicProcessor userSignUpTopicProcessor)
    {
        this.userSignUpTopicProcessor=userSignUpTopicProcessor;
    }
    @Override
    public void notifyUserSignUpTopic(User user, RoleType roleType) throws JsonProcessingException {
        userSignUpTopicProcessor.notifyUserSignUpTopic(user,roleType);
    }
}
