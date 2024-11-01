package com.example.userauthjwt.ExternalLibrary.AwsSNS.Service;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface AwsSNSService {
    void publishUserSignUpTopic(String newUserObject) throws JsonProcessingException;
}
