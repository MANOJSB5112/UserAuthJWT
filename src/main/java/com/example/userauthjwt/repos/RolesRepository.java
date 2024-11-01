package com.example.userauthjwt.repos;

import com.example.userauthjwt.models.RoleType;
import com.example.userauthjwt.models.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolesRepository extends JpaRepository<Roles, Long> {

    Optional<Roles> findByRoleType(RoleType roleType);
}