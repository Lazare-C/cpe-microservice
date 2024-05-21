package com.sp.controller;

import com.sp.model.bo.UserBo;
import com.sp.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {

    private final AuthService authService;


    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @RequestMapping(value = {"/register"}, method = RequestMethod.POST)
    public String register(@RequestParam("username") String username, @RequestParam("password") String password, Model model) {

        if (this.authService.getUser() != null) {
            return "homePage";
        }

        try {
            this.authService.registerUser(username, password);
            return "login";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @PostMapping("/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password, Model model) {

        if (this.authService.getUser() != null) {
            return "homePage";
        }
        UserBo userBo = this.authService.loginUser(username, password);
        try {
            if (userBo != null) {
                model.addAttribute("myUser", userBo);
                return "homePage";
            } else {
                return null;
            }
        } catch (Exception e) {
            return e.getMessage();
        }

    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout() {
        this.authService.logoutUser();
        return ResponseEntity.ok("success");
    }


}
