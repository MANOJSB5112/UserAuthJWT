package com.example.userauthjwt.services;

import com.example.userauthjwt.ExceptionPackage.UserAlreadyExistException;
import com.example.userauthjwt.NewUserEventPackage.ActionsUponUserSignUp;
import com.example.userauthjwt.models.RoleType;
import com.example.userauthjwt.models.Roles;
import com.example.userauthjwt.models.Token;
import com.example.userauthjwt.models.User;
import com.example.userauthjwt.repos.RolesRepository;
import com.example.userauthjwt.repos.TokensRepo;
import com.example.userauthjwt.repos.UserRepo;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

@Service
public class UserServiceClass implements UserService{

    private BCryptPasswordEncoder passwordEncoder;
    private UserRepo userRepo;
    private TokensRepo tokensRepo;
    private ActionsUponUserSignUp actionsUponSignUp;
    private RolesRepository rolesRepository;



    @Autowired
    public UserServiceClass(UserRepo userRepo,BCryptPasswordEncoder bCryptPasswordEncoder,TokensRepo tokensRepo,
                            ActionsUponUserSignUp actionsUponSignUp,RolesRepository rolesRepository)
    {
        this.userRepo=userRepo;
        this.passwordEncoder=bCryptPasswordEncoder;
        this.tokensRepo=tokensRepo;
        this.actionsUponSignUp=actionsUponSignUp;
        this.rolesRepository=rolesRepository;
    }

    public User signUp(String name, String email, String password, String phoneNumber, RoleType roleType) throws Exception {
       Optional<User> userOptional=userRepo.findByEmail(email);
       Optional<Roles> rolesOptional=rolesRepository.findByRoleType(roleType);
       if(rolesOptional.isEmpty())
       {
           throw new Exception("Something went wrong, no roles present with role type "+roleType);
       }
       Roles role=rolesOptional.get();
       if(userOptional.isPresent() ) {
           User savedUser = userOptional.get();
           for (Roles userRole : savedUser.getRoles()) {
               if (userRole.getRoleType().equals(roleType)) {
                   throw new UserAlreadyExistException("User Account with the email address " + email + " already present as a " + roleType);
               }
           }
           savedUser.getRoles().add(role);
           savedUser=userRepo.save(savedUser);
           actionsUponSignUp.notifyUserSignUpTopic(savedUser,roleType);
           return savedUser;
       }
        User user=new User();
        user.setName(name);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        user.setHashedPassword(passwordEncoder.encode(password));
        if(user.getRoles()==null)
        {
            user.setRoles(new ArrayList<>());
        }
        user.getRoles().add(role);
        user=userRepo.save(user);
        actionsUponSignUp.notifyUserSignUpTopic(user,roleType);
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
