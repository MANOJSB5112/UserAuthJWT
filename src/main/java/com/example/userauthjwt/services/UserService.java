package com.example.userauthjwt.services;

import com.example.userauthjwt.models.Tokens;

public interface UserService {
    public void signUp(String name,String email,String Password) throws Exception;

    public Tokens login(String email, String hashedPassword) throws Exception;
    public String logout(String tk);
}
