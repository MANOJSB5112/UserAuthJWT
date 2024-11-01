package com.example.userauthjwt.models;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Roles extends BaseModel{
    private String name;
    @Enumerated(EnumType.ORDINAL)
    private RoleType roleType;
}
