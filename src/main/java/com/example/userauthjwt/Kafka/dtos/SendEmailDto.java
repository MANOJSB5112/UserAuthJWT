package com.example.userauthjwt.Kafka.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SendEmailDto{
    private String to;
    private String from;
    private String Subject;
    private String body;
}
