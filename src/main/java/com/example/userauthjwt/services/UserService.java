package com.example.userauthjwt.services;

public interface UserService {
    public void signUp(String name,String email,String Password) throws Exception;

    public void login(String email, String hashedPassword) throws Exception;
}
