package com.example.userauthjwt.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Signupdto {
    private String name;
    private String email;
    private String password;
    private String phoneNumber;
}
