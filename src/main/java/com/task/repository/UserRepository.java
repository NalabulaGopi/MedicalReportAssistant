package com.task.repository;

import com.task.Model.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserDetails, Integer> {

    // Find user by email (used for login & register check)
    Optional<UserDetails> findByEmailId(String emailId);

    // Check if email already exists (alternative to Optional check)
    boolean existsByEmailId(String emailId);

    // (Optional) Find user by email and password (not recommended for real apps)
    Optional<UserDetails> findByEmailIdAndPassword(String emailId, String password);
}