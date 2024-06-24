package com.example.userauthjwt.dtos;

import com.example.userauthjwt.models.User;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class TokenDto {
    private User user;
    private String value;
    private Date expiryAt;
}
