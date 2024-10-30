package com.example.userauthjwt.services;

import com.example.userauthjwt.ExceptionPackage.UserAlreadyExistException;
import com.example.userauthjwt.NewUserEventPackage.ActionsUponUserSignUp;
import com.example.userauthjwt.models.Token;
import com.example.userauthjwt.models.User;
import com.example.userauthjwt.repos.TokensRepo;
import com.example.userauthjwt.repos.UserRepo;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Optional;

@Service
public class UserServiceClass implements UserService{

   private BCryptPasswordEncoder passwordEncoder;
    private UserRepo userRepo;
    private TokensRepo tokensRepo;
    private ActionsUponUserSignUp actionsUponSignUp;



    @Autowired
    public UserServiceClass(UserRepo userRepo,BCryptPasswordEncoder bCryptPasswordEncoder,TokensRepo tokensRepo,
                            ActionsUponUserSignUp actionsUponSignUp)
    {
        this.userRepo=userRepo;
        this.passwordEncoder=bCryptPasswordEncoder;
        this.tokensRepo=tokensRepo;
        this.actionsUponSignUp=actionsUponSignUp;
    }

    public User signUp(String name,String email,String password,String phoneNumber) throws UserAlreadyExistException, JsonProcessingException {
       Optional<User> savedUser=userRepo.findByEmail(email);
       if(savedUser.isPresent())
       {
         throw new UserAlreadyExistException("User with the email address "+email+ " already present");
       }
        User user=new User();
        user.setName(name);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        user.setHashedPassword(passwordEncoder.encode(password));
        user=userRepo.save(user);
        actionsUponSignUp.notifyUserUponSignUp(user);
        actionsUponSignUp.createUserInInventoryService(user);
        return user;
    }

    @Override
    public Token login(String email, String password) throws Exception {
        Optional<User> savedUser=userRepo.findByEmail(email);
        if(savedUser.isPresent())
        {
            User user= savedUser.get();
            if(!passwordEncoder.matches(password, user.getHashedPassword()))
            {
                throw new Exception("Password is incorrect");
            }else {
                LocalDate today = LocalDate.now();
                LocalDate thirtyDaysLater = today.plus(30, ChronoUnit.DAYS);

                // Convert LocalDate to Date
                Date expiryDate = Date.from(thirtyDaysLater.atStartOfDay(ZoneId.systemDefault()).toInstant());
                Token token =new Token();
                token.setUser(user);
                token.setExpiryAt(expiryDate);
                token.setValue(RandomStringUtils.randomAlphanumeric(128));
                tokensRepo.save(token);
                return token;
            }
        }else
        {
            throw new Exception("No Profile present with this email Signup instead");
        }

    }

    @Override
    public String logout(String tk) {
        Optional<Token> tokens=tokensRepo.findByValueAndDeletedEquals(tk,false);
        if(tokens.isPresent())
        {
            Token tk1=tokens.get();
            tk1.setDeleted(true);
            tokensRepo.save(tk1);
        }
        return "Your are successfully Logged out";
    }
    public User validateToken(String token) {
        Optional<Token> tkn = tokensRepo.
                findByValueAndDeletedEqualsAndExpiryAtGreaterThan(token, false, new Date());

        if (tkn.isEmpty()) {
            return null;
        }

        return tkn.get().getUser();
    }
}
