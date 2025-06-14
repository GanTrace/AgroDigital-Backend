package com.acme.agrodigitalbackend.users.infrastructure.persistence.jpa.repositories;

import com.acme.agrodigitalbackend.users.domain.model.aggregates.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail_Address(String email);
    boolean existsByEmail_Address(String email);
}