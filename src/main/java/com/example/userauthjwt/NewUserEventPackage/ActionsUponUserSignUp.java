package com.example.userauthjwt.NewUserEventPackage;

import com.example.userauthjwt.models.User;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface ActionsUponUserSignUp {
    void notifyUserUponSignUp(User user) throws JsonProcessingException;
    void createUserInInventoryService(User user);
}
