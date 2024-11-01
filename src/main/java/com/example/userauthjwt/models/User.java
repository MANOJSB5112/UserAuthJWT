package com.example.userauthjwt.models;

import jakarta.persistence.*;
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
    @JoinTable(
            name = "user_roles", // The name of the join table in the database
            joinColumns = @JoinColumn(name = "user_id"), // The column in the join table that refers to the User entity
            inverseJoinColumns = @JoinColumn(name = "roles_id") // The column in the join table that refers to the Roles entity
    )
    private List<Roles> roles;
    private Boolean isEmailVerified;
}
