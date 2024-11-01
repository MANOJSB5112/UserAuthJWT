package com.example.userauthjwt.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewUserExternalDto {
    private long userId;
    private String name;
    private String email;
    private String phoneNumber;
    private String roleName;
}
