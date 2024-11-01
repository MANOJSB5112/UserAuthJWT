package com.example.userauthjwt.ObjectMapper;

import com.example.userauthjwt.models.RoleType;
import org.springframework.stereotype.Component;

@Component
public class Utility {

    public String getRoleNameForRoleType(RoleType roleType)
    {
        if(roleType.equals(RoleType.CUSTOMER))
        {
            return "Customer";
        }else if(roleType.equals(RoleType.SELLER))
        {
            return "Seller";
        } else if (roleType.equals(RoleType.ADMIN)) {
            return "Admin";
        }
        return "";
    }
}
