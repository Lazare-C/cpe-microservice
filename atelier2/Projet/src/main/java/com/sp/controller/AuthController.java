package com.sp.controller;

import com.sp.service.AuthService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {


    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestParam("username") String username, @RequestParam("password") String password) {
        this.authService.registerUser(username, password);
        return ResponseEntity.ok("success");
    }

    @PostMapping("/login")
    public void login(@RequestParam("username") String username, @RequestParam("password") String password, HttpServletResponse response) {
        var token = this.authService.loginUser(username, password);
        response.addCookie(token);
        ResponseEntity.ok("success");
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletResponse response) {
        Cookie cookie = this.authService.logout();
        response.addCookie(cookie);
        return ResponseEntity.ok("success");
    }


}
