package fr.dreamteam.account.service;

import dto.UserDto;
import fr.dreamteam.account.bo.UserBo;
import fr.dreamteam.account.exception.LoginException;
import fr.dreamteam.account.mapper.UserMapper;
import fr.dreamteam.account.repository.UserRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.*;

@Service
public class AuthService {

    public final String SESSION_COOKIE_NAME = "sessionId";
    private final Map<String, UserBo> sessionList = new HashMap<>();

    private final UserRepository userRepository;
    private final HttpServletRequest httpServletRequest;
    private final HttpServletResponse httpServletResponse;
    private final UserMapper userMapper;
    private final WebClient webClient;

    public AuthService(UserRepository userRepository, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.httpServletRequest = httpServletRequest;
        this.httpServletResponse = httpServletResponse;
        this.userMapper = userMapper;
        webClient = WebClient.create();
    }

    public UserDto registerUser(String username, String password) {
        if (this.userRepository.findByUsername(username) != null) {
            throw new LoginException("User already exists");
        }
        UserBo userBo = this.userRepository.saveAndFlush(new UserBo(username, password));
        this.initCards(userBo.getId());
        return this.userMapper.toDto(userBo);
    }

    public UserDto loginUser(String username, String password) {

        if (this.getUser() != null) {
            this.sessionList.remove(this.httpServletRequest.getCookies()[0].getValue());
        }

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
        cookie.setPath("/");
        this.httpServletResponse.addCookie(cookie);
        return this.userMapper.toDto(user);
    }

    /**
     * Permet de récupérer l'utilisateur courant, si null alors pas d'utilisateur
     *
     * @return null si pas d'utilisateur, sinon l'utilisateur courant
     */


    public UserBo getUser() {
        return this.getUser(null);
    }

    public UserBo getUser(String session) {

        if (session != null) {
            UserBo userBo = this.sessionList.get(session);
            if (userBo != null) {
                return this.userRepository.findById(userBo.getId()).orElseThrow(() -> {
                    this.logoutUser();
                    return new LoginException("Session not found");
                });
            }
        }

        if (this.httpServletRequest.getCookies() == null) {
            return null;
        }
        Cookie cookie = Arrays.stream(this.httpServletRequest.getCookies())
                .filter(c -> c.getName().equals(SESSION_COOKIE_NAME)).findAny().orElse(null);
        if (cookie == null) {
            return null;
        }
        UserBo userBo = this.sessionList.get(cookie.getValue());
        if (userBo == null) {
            return null;
        }
        return this.userRepository.findById(userBo.getId()).orElseThrow(() -> {
            this.logoutUser();
            return new LoginException("Session not found");
        });
    }

    public Optional<UserBo> getUserOptional() {
        return Optional.ofNullable(this.getUser());
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

    public UserDto getUserDto(String session) {
        UserBo user = this.getUser(session);
        return this.userMapper.toDto(user);
    }


    public void initCards(Long userId) {

        WebClient client = WebClient.create("http://localhost:8080/card");
        client.post()
                .uri(uriBuilder -> uriBuilder.path("/initiate").queryParam("userId", userId.toString()).build())
                .accept(MediaType.APPLICATION_JSON).retrieve().bodyToMono(String.class)
                .block();
    }

}
