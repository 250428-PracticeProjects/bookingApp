package com.example.revature.bookingApp.controller;


import com.example.revature.bookingApp.dto.LoginDto;
import com.example.revature.bookingApp.dto.RegisterDto;
import com.example.revature.bookingApp.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private AuthService authService;

    //Builds Register rest API
    @PostMapping("/register")
    //we use it to make incoming http request the come into this method
    public ResponseEntity<String> resgister(@RequestBody RegisterDto resgisterDto) {
        String response = authService.register(resgisterDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    //Build Login REST API
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto) {
       String response = authService.login(loginDto);
       return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
