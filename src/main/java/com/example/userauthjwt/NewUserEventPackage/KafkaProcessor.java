package com.example.userauthjwt.NewUserEventPackage;

import com.example.userauthjwt.ExternalLibrary.Kafka.service.KafkaService;
import com.example.userauthjwt.ObjectMapper.ObjectMapperUtil;
import com.example.userauthjwt.models.RoleType;
import com.example.userauthjwt.models.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Component;

@Component
public class KafkaProcessor implements UserSignUpTopicProcessor{
    private KafkaService kafkaService;
    private ObjectMapperUtil objectMapperUtil;

    public KafkaProcessor(KafkaService kafkaService,ObjectMapperUtil objectMapperUtil)
    {
        this.kafkaService=kafkaService;
        this.objectMapperUtil=objectMapperUtil;
    }

    @Override
    public void notifyUserSignUpTopic(User user, RoleType roleType) throws JsonProcessingException {
        String newUserObject=objectMapperUtil.getNewUserObjectForRoleType(user,roleType);
        kafkaService.publishUserSignUpTopic(newUserObject);
    }

}
