package com.example.userauthjwt.repos;

import com.example.userauthjwt.models.Tokens;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface TokensRepo extends JpaRepository<Tokens,Long> {

    Tokens save(Tokens token);

    Optional<Tokens> findByValueAndDeletedEquals(String value, boolean isDeleted);

    Optional<Tokens> findByValueAndDeletedEqualsAndExpiryAtGreaterThan(String value, boolean isDeleted, Date expiryGreaterThan);
}
