package com.example.userauthjwt.controllers;

import com.example.userauthjwt.dtos.ResponseStatus;
import com.example.userauthjwt.dtos.*;
import com.example.userauthjwt.models.RoleType;
import com.example.userauthjwt.models.Token;
import com.example.userauthjwt.models.User;
import com.example.userauthjwt.services.RolesUpdateService;
import com.example.userauthjwt.services.UserService;
import io.micrometer.common.lang.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RestController
@RequestMapping("/auth")
public class UserController {

    private UserService userService;
    private RolesUpdateService rolesUpdateService;

    @Autowired
    UserController(UserService userService,RolesUpdateService rolesUpdateService)
    {
        this.userService=userService;
        this.rolesUpdateService=rolesUpdateService;

    }

    @PostMapping("/customer/signup")
    public ResponseEntity<SignupUserResponseDto> customerSignUp(@RequestBody SignupUserRequestDto request) throws Exception {
        String name= request.getName();
        String email=request.getEmail();
        String password=request.getPassword();
        String phoneNumber=request.getPhoneNumber();
        User user=userService.signUp(name,email,password,phoneNumber,RoleType.CUSTOMER);
        SignupUserResponseDto responseDto=new SignupUserResponseDto();
        responseDto.setUser(user);
        responseDto.setResponseStatus(ResponseStatus.SUCCESS);
        return new ResponseEntity<>(responseDto,HttpStatus.CREATED);
    }
    @PostMapping("/seller/signup")
    public ResponseEntity<SignupUserResponseDto> sellerSignUp(@RequestBody SignupUserRequestDto request) throws Exception {
        String name= request.getName();
        String email=request.getEmail();
        String password=request.getPassword();
        String phoneNumber=request.getPhoneNumber();
        User user=userService.signUp(name,email,password,phoneNumber, RoleType.SELLER);
        SignupUserResponseDto responseDto=new SignupUserResponseDto();
        responseDto.setUser(user);
        responseDto.setResponseStatus(ResponseStatus.SUCCESS);
        return new ResponseEntity<>(responseDto,HttpStatus.CREATED);
    }

    @PostMapping("/login")
     public ResponseEntity<TokenDto> loginIn(@RequestBody LoginDto loginDto) throws Exception {
        String email=loginDto.getEmail();
        String password=loginDto.getPassword();
        try {
            Optional<Token> tokens= Optional.ofNullable(userService.login(email, password));

            if (tokens.isPresent()) {
               Token tk=tokens.get();
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

    @PostMapping("/validate/{token}")
    public UserDto validateToken(@PathVariable("token") @NonNull String token) {
        return UserDto.from(userService.validateToken(token));
    }
    @PutMapping("/update/RoleRepo/{userId}")
    public void updateRoleRepo(@PathVariable("userId") long usedId) {
       rolesUpdateService.updateRolesWithDefaultRoleType();
    }

}
