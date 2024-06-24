package com.example.userauthjwt.services;

import com.example.userauthjwt.models.Tokens;
import com.example.userauthjwt.models.User;
import com.example.userauthjwt.repos.TokensRepo;
import com.example.userauthjwt.repos.UserRepo;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Optional;

@Service
public class UserServiceClass implements UserService {

   BCryptPasswordEncoder passwordEncoder;
    UserRepo userRepo;
    TokensRepo tokensRepo;
    UserServiceClass(UserRepo userRepo,BCryptPasswordEncoder bCryptPasswordEncoder,TokensRepo tokensRepo)
    {
        this.userRepo=userRepo;
        this.passwordEncoder=bCryptPasswordEncoder;
        this.tokensRepo=tokensRepo;

    }

    public void signUp(String name,String email,String password) throws Exception {
          User u=new User();
          u.setName(name);
          u.setEmail(email);
          u.setHashedPassword(passwordEncoder.encode(password));

          Optional<User> savedUser=userRepo.findByEmail(u.getEmail());

          if(savedUser.isPresent())
          {
              throw new Exception("User with this email already present");
          }else
          {
              userRepo.save(u);
          }

    }

    @Override
    public Tokens login(String email, String password) throws Exception {
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
                Tokens token =new Tokens();
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
        Optional<Tokens> tokens=tokensRepo.findByValueAndDeletedEquals(tk,false);
        if(tokens.isPresent())
        {
            Tokens tk1=tokens.get();
            tk1.setDeleted(true);
            tokensRepo.save(tk1);
        }
        return "Your are successfully Logged out";
    }
}
