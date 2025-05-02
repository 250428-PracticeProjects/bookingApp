package com.example.revature.bookingApp.service.impl;

import com.example.revature.bookingApp.dto.LoginDto;
import com.example.revature.bookingApp.dto.RegisterDto;
import com.example.revature.bookingApp.entity.Role;
import com.example.revature.bookingApp.entity.User;
import com.example.revature.bookingApp.exception.BookingAPIException;
import com.example.revature.bookingApp.repository.RoleRepository;
import com.example.revature.bookingApp.repository.UserRepository;
import com.example.revature.bookingApp.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;


@Service
@AllArgsConstructor
public class AuthServiceimpl implements AuthService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager; // injection of authentication manager


    @Override
    public String register(RegisterDto registerDto) {
        //check if user name already exists
        if(userRepository.existsByUsername(registerDto.getUsername())){
            throw new BookingAPIException(HttpStatus.BAD_REQUEST, "User name already exists!");
        }
        // checks if email already exists in database
        if(userRepository.existsByEmail(registerDto.getEmail())) {
            throw new BookingAPIException(HttpStatus.BAD_REQUEST, "Email already exists!");
        }

        //user object which stores object into database
        User user = new User();
        //sets all details from dto into the user object
        user.setName(registerDto.getName());
        user.setUsername(registerDto.getUsername());
        user.setEmail(registerDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

        //assigning role to the user by getting it from db
        Set<Role> roles = new HashSet<>();
        //stores user role to db
       Role userRole = roleRepository.findByName("ROLE_USER");
       //adding user role to the set
        roles.add(userRole);
        //sets rules to the user
        user.setRoles(roles);

        userRepository.save(user);

        return "User registered successfully!";
    }


    @Override
    public String login(LoginDto loginDto) {

        //calling authentication manager
       Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsernameOrEmail(),
                loginDto.getPassword()
        ));
        //stores authentication in the security context holder so spring security uses it
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return "User logged in successfully!";
    }
}
