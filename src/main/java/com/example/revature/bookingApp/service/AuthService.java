package com.example.revature.bookingApp.service;

import com.example.revature.bookingApp.dto.LoginDto;
import com.example.revature.bookingApp.dto.RegisterDto;

public interface AuthService {
    String register(RegisterDto registerDto);

    //Login auth
    String login(LoginDto loginDto);
}
