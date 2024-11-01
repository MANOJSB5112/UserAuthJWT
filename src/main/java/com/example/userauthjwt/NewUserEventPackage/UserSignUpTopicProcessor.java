package com.example.userauthjwt.NewUserEventPackage;

import com.example.userauthjwt.models.RoleType;
import com.example.userauthjwt.models.User;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface UserSignUpTopicProcessor {
    void notifyUserSignUpTopic(User user, RoleType roleType) throws JsonProcessingException;

}
