package com.example.userauthjwt.services;

import com.example.userauthjwt.models.Token;
import com.example.userauthjwt.models.User;

public interface UserService {
    public void signUp(String name,String email,String Password) throws Exception;

    public Token login(String email, String hashedPassword) throws Exception;
    public String logout(String tk);
    public User validateToken(String token);
}
