package com.example.userauthjwt.services;

import com.example.userauthjwt.models.RoleType;
import com.example.userauthjwt.models.Roles;
import com.example.userauthjwt.repos.RolesRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolesUpdateService {

    @Autowired
    private RolesRepository rolesRepository;

    @Transactional
    public void updateRolesWithDefaultRoleType() {
        List<Roles> rolesList = rolesRepository.findAll();
        for (Roles role : rolesList) {
            if (role.getRoleType() == null) {
                if(role.getName().equals("Customer"))
                {
                    role.setRoleType(RoleType.CUSTOMER);
                }else if(role.getName().equals("Seller"))
                {
                    role.setRoleType(RoleType.SELLER);
                }else {
                    role.setRoleType(RoleType.ADMIN);
                }
                rolesRepository.save(role);
            }
        }
    }
}