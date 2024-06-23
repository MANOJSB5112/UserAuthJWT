package com.example.userauthjwt.services;

import com.example.userauthjwt.models.User;
import com.example.userauthjwt.repos.UserRepo;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class UserServiceClass implements UserService {

    UserRepo userRepo;
    UserServiceClass(UserRepo userRepo)
    {
        this.userRepo=userRepo;
    }

    public void signUp(String name,String email,String password) throws Exception {
          User u=new User();
          u.setName(name);
          u.setEmail(email);
          u.setHashedPassword(password);

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
    public void login(String email, String hashedPassword) throws Exception {
        Optional<User> savedUser=userRepo.findByEmail(email);
        if(savedUser.isPresent())
        {
            User user= savedUser.get();
            if(!Objects.equals(user.getHashedPassword(), hashedPassword))
            {
                throw new Exception("Password is incorrect");
            }
        }else
        {
            throw new Exception("No Profile resent with this email Signup instead");
        }
    }
}
