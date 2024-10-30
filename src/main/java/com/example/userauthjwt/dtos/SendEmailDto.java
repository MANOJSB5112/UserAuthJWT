package com.example.userauthjwt.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SendEmailDto{
    private String userName;
    private String to;
}
