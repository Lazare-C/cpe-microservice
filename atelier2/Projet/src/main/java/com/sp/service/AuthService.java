package com.sp.service;

import com.sp.exception.LoginException;
import com.sp.model.bo.UserBo;
import com.sp.repository.UserRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class AuthService {

    public final String SESSION_COOKIE_NAME = "sessionId";
    private final Map<String, UserBo> sessionList = new HashMap<>();

    private final UserRepository userRepository;
    private final HttpServletRequest httpServletRequest;
    private final HttpServletResponse httpServletResponse;

    public AuthService(UserRepository userRepository, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        this.userRepository = userRepository;
        this.httpServletRequest = httpServletRequest;
        this.httpServletResponse = httpServletResponse;
    }

    public void registerUser(String username, String password) {
        if (this.userRepository.findByUsername(username) != null) {
            throw new LoginException("User already exists");
        }
        //TODO: add conditional to check if username is already taken

        //TODO: add hashing for password
        this.userRepository.save(new UserBo(username, password));
    }

    public void loginUser(String username, String password) {
        UserBo user = this.userRepository.findByUsername(username);
        if (user == null) {
            throw new LoginException("User does not exist");
        }
        if (!user.getPassword().equals(password)) {
            throw new LoginException("Incorrect password");
        }
        String sessionToken = randomString(32);
        this.sessionList.put(sessionToken, user);

        Cookie cookie = new Cookie(SESSION_COOKIE_NAME, sessionToken);
        cookie.setMaxAge(60 * 60 * 24 * 365);
        cookie.isHttpOnly();
        this.httpServletResponse.addCookie(cookie);
    }

    /**
     * Permet de récupérer l'utilisateur courant, si null alors pas d'utilisateur
     *
     * @return null si pas d'utilisateur, sinon l'utilisateur courant
     */
    public UserBo getUser() {
        if (this.httpServletRequest.getCookies() == null) {
            return null;
        }
        Cookie cookie = Arrays.stream(this.httpServletRequest.getCookies())
                .filter(c -> c.getName().equals(SESSION_COOKIE_NAME)).findAny().orElse(null);
        if (cookie == null) {
            return null;
        }
        UserBo userBo = this.sessionList.get(cookie.getValue());
        return this.userRepository.findById(userBo.getId()).orElseThrow(() -> {
            this.logoutUser();
            return new LoginException("Session not found");
        });
    }


    private String randomString(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder result = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            result.append(characters.charAt(random.nextInt(characters.length())));
        }
        return result.toString();
    }


    public void logoutUser() {
        Arrays.stream(this.httpServletRequest.getCookies())
                .filter(cookie -> cookie.getName().equals(SESSION_COOKIE_NAME)).forEach(cookie -> {
                    this.sessionList.remove(cookie.getValue());
                });
        Cookie cookie = new Cookie(SESSION_COOKIE_NAME, null);
        cookie.setMaxAge(0);
        this.httpServletResponse.addCookie(cookie);

    }
}
