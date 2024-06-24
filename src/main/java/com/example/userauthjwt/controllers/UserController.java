package com.example.userauthjwt.controllers;

import com.example.userauthjwt.dtos.LoginDto;
import com.example.userauthjwt.dtos.LogoutDto;
import com.example.userauthjwt.dtos.Signupdto;
import com.example.userauthjwt.dtos.TokenDto;
import com.example.userauthjwt.models.Tokens;
import com.example.userauthjwt.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

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
     public ResponseEntity<TokenDto> loginIn(@RequestBody LoginDto loginDto) throws Exception {
        String email=loginDto.getEmail();
        String password=loginDto.getPassword();
        try {
            Optional<Tokens> tokens= Optional.ofNullable(userService.login(email, password));

            if (tokens.isPresent()) {
               Tokens tk=tokens.get();
                TokenDto tokenDto=new TokenDto();
                tokenDto.setUser(tk.getUser());
                tokenDto.setValue(tk.getValue());
                tokenDto.setExpiryAt(tk.getExpiryAt());
                return ResponseEntity.ok(tokenDto);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
     }


     @PostMapping("/logout")
     public String logout(@RequestBody LogoutDto request)
     {
             return userService.logout(request.getToken());
     }

}
