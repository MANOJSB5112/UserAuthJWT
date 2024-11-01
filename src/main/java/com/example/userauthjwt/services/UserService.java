package com.example.userauthjwt.services;

import com.example.userauthjwt.ExceptionPackage.UserAlreadyExistException;
import com.example.userauthjwt.models.RoleType;
import com.example.userauthjwt.models.Token;
import com.example.userauthjwt.models.User;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface UserService {
    public User signUp(String name, String email, String Password, String phoneNumber, RoleType roleType) throws Exception;

    public Token login(String email, String hashedPassword) throws Exception;
    public String logout(String tk);
    public User validateToken(String token);
}
