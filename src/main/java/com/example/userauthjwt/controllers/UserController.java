package com.example.userauthjwt.controllers;

import com.example.userauthjwt.dtos.LoginDto;
import com.example.userauthjwt.dtos.Signupdto;
import com.example.userauthjwt.models.User;
import com.example.userauthjwt.services.UserService;
import com.example.userauthjwt.services.UserServiceClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController
@RequestMapping("/users")
public class UserController {

    UserService userService;

    @Autowired
    UserController(UserService userService)
    {
        this.userService=userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody Signupdto request) throws Exception {
        String name= request.getName();
        String email=request.getEmail();
        String password=request.getPassword();
        userService.signUp(name,email,password);
        return ResponseEntity.ok("User signed up successfully");
    }

    @PostMapping("/login")
     public ResponseEntity<String> loginIn(@RequestBody LoginDto loginDto) throws Exception {
        String email=loginDto.getEmail();
        String password=loginDto.getPassword();
        userService.login(email,password);
        return ResponseEntity.ok("User login was SuccessFull");
     }

}
