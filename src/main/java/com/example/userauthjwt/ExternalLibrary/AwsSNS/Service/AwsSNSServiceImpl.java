package com.example.userauthjwt.ExternalLibrary.AwsSNS.Service;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.aws.messaging.core.NotificationMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class AwsSNSServiceImpl implements AwsSNSService{

    private final AmazonSNS amazonSNS;
    private final NotificationMessagingTemplate notificationMessagingTemplate;

    private final String userSignupTopicArn = "arn:aws:sns:eu-north-1:992382598022:UserSignUpTopic"; // Ensure this is correct

    @Autowired
    public AwsSNSServiceImpl(AmazonSNS amazonSNS, NotificationMessagingTemplate notificationMessagingTemplate) {
        this.amazonSNS = amazonSNS;
        this.notificationMessagingTemplate = notificationMessagingTemplate;
    }

    @Override
    public void notifyUserUponSignUp(String emailObject) throws JsonProcessingException {
        // Use the class-level userSignupTopicArn directly
        PublishRequest publishRequest = new PublishRequest()
                .withTopicArn(userSignupTopicArn) // Use the defined ARN
                .withMessage(emailObject)          // Message body
                .withSubject("NewUserSignUp");     // Message subject

        // Publish the message to SNS
        PublishResult publishResult = amazonSNS.publish(publishRequest);

        // Log the message ID returned from SNS
        System.out.println("Message sent with ID: " + publishResult.getMessageId());
    }

    @Override
    public void createUserInInventoryService(String userCreationObject) throws JsonProcessingException {
        
    }
}
