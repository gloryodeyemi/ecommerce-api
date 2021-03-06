package com.example.ecommerce.repositories;

import com.example.ecommerce.models.ERole;
import com.example.ecommerce.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findById(Long id);
    Boolean existsByName(String name);
}
