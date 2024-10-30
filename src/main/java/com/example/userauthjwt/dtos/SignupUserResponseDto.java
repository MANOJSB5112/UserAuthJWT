package com.example.userauthjwt.dtos;

import com.example.userauthjwt.models.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupUserResponseDto {
    private User user;
    private ResponseStatus responseStatus;
}
