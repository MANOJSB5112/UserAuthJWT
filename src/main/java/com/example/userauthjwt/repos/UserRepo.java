package com.example.userauthjwt.repos;

import com.example.userauthjwt.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User,Long> {
    User save(User user);
    Optional<User> findByEmail(String email);
}
