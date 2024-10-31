package com.example.userauthjwt.ExternalLibrary.AwsSNS.Service;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface AwsSNSService {
    void notifyUserUponSignUp(String emailObject) throws JsonProcessingException;
    void createUserInInventoryService(String userCreationObject) throws JsonProcessingException;
}
