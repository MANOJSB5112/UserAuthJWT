package com.example.userauthjwt.NewUserEventPackage;

import com.example.userauthjwt.ExternalLibrary.Kafka.service.KafkaService;
import com.example.userauthjwt.ObjectMapper.ObjectMapperUtil;
import com.example.userauthjwt.models.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Component;

@Component
public class KafkaProcessor implements NewUserEventProcessor{
    private KafkaService kafkaService;
    private ObjectMapperUtil objectMapperUtil;

    public KafkaProcessor(KafkaService kafkaService,ObjectMapperUtil objectMapperUtil)
    {
        this.kafkaService=kafkaService;
        this.objectMapperUtil=objectMapperUtil;
    }

    @Override
    public void notifyUserUponSignUp(User user) throws JsonProcessingException {
        String emailObject=objectMapperUtil.getNewUserNotificationObject(user);
        kafkaService.notifyUserUponSignUp(emailObject);
    }

    @Override
    public void createUserInInventoryService(User user) throws JsonProcessingException {
        String userCreationObject=objectMapperUtil.getNewUserCreationObject(user);
        kafkaService.createUserInInventoryService(userCreationObject);

    }
}
