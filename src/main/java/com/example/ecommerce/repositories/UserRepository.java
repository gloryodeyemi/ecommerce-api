package com.example.ecommerce.repositories;

import com.example.ecommerce.models.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserAccount, Long> {
    Optional<UserAccount> findByEmailAddress(String emailAddress);
    Boolean existsByEmailAddress(String emailAddress);
}
