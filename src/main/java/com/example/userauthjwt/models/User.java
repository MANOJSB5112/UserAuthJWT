package com.example.userauthjwt.models;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class User extends BaseModel{
    private String name;
    private String email;
    private String hashedPassword;
    private String phoneNumber;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Roles> roles;
    private Boolean isEmailVerified;
}
