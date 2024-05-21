package com.sp.service;

import com.sp.exception.LoginException;
import com.sp.model.bo.UserBo;
import com.sp.repository.UserRepository;
import jakarta.servlet.http.Cookie;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class AuthService {

    private final Map<UserBo, String> sessionList = new HashMap<>();


    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void registerUser(String username, String password) {
        if (this.userRepository.findByUsername(username) != null) {
            throw new LoginException("User already exists");
        }
        //TODO: add conditional to check if username is already taken

        //TODO: add hashing for password
        this.userRepository.save(new UserBo(username, password));
    }


    public Cookie loginUser(String username, String password) {
        UserBo user = this.userRepository.findByUsername(username);
        if (user == null) {
            throw new LoginException("User does not exist");
        }
        if (!user.getPassword().equals(password)) {
            throw new LoginException("Incorrect password");
        }
        String sessionToken = randomString(32);
        this.sessionList.put(user, sessionToken);

        Cookie cookie = new Cookie("sessionId", sessionToken);
        cookie.setPath("/");
        cookie.setMaxAge(60 * 60 * 24 * 365);
        cookie.isHttpOnly();
        return cookie;
    }

    public boolean checkToken(String token){
        return this.sessionList.containsValue(token);
    }



    private final String randomString(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder result = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            result.append(characters.charAt(random.nextInt(characters.length())));
        }
        return result.toString();
    }

    public Cookie logout() {
        Cookie cookie = new Cookie("sessionId", null);
        cookie.setMaxAge(0);
        return cookie;
    }
}
