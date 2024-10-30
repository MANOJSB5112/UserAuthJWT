package com.example.userauthjwt.NewUserEventPackage;

import com.example.userauthjwt.models.User;

public interface ActionsUponUserSignUp {
    void notifyUserUponSignUp(User user);
    void createUserInInventoryService(User user);
}
