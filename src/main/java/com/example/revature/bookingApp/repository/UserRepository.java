package com.example.revature.bookingApp.repository;

import com.example.revature.bookingApp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // the entity is User and User has a field name called username
    Optional<User> findByUsername(String username);

    Boolean existsByEmail(String email);

    Optional<User> findByUsernameOrEmail(String username, String email);
    //these will be passed to authServiceImpl class as a custom method to check if user name exists
    Boolean existsByUsername(String username);
}
