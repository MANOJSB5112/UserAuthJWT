package com.example.userauthjwt.ExceptionPackage;

public class UserAlreadyExistException extends Exception{
    public UserAlreadyExistException(String message)
    {
        super(message);
    }
}
