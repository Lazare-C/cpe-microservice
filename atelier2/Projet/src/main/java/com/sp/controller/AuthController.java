package com.sp.controller;

import com.sp.model.UserDto;
import com.sp.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @RequestMapping(value = {"/register"}, method = RequestMethod.POST)
    public ResponseEntity<UserDto> register(@RequestParam("username") String username, @RequestParam("password") String password) {
        UserDto userDto = this.authService.registerUser(username, password);
        return ResponseEntity.ok(userDto);
    }

    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestParam("username") String username, @RequestParam("password") String password) {
        return ResponseEntity.ok(this.authService.loginUser(username, password));

    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout() {
        this.authService.logoutUser();
        return ResponseEntity.ok("success");
    }

    @GetMapping("/currentUser")
    public ResponseEntity<UserDto> getCurrentUser() {
        return ResponseEntity.ok(this.authService.getUserDto());
    }


}
