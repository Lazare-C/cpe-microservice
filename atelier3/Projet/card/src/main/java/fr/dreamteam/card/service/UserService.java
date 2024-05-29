package fr.dreamteam.card.service;


import com.netflix.discovery.EurekaClient;
import dto.UserDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;

@Service
public class UserService {

    private static final String ACCOUNT_SERVICE = "ACCOUNT";
    private final WebClient webClient;
    private final EurekaClient eurekaClient;
    private final HttpServletRequest httpServletRequest;


    public UserService(EurekaClient eurekaClient, HttpServletRequest httpServletRequest) {
        this.eurekaClient = eurekaClient;
        this.httpServletRequest = httpServletRequest;
        webClient = WebClient.create();
    }

    public UserDto getUserById(Long userId) {
        return webClient.get().uri(getAccountUService() + "/" + userId).retrieve().bodyToMono(UserDto.class).block();
    }

    public UserDto getCurrentUser() {
        String value = Arrays.stream(httpServletRequest.getCookies())
                .filter(cookie -> cookie.getName().equals("sessionId")).findFirst()
                .orElseThrow(() -> new RuntimeException("No sessionId cookie")).getValue();
       return this.getUser(value);
     //   return webClient.get().uri(getAccountUService() + "/currentUser").retrieve().bodyToMono(UserDto.class).block();
    }

    public UserDto getUser(String sessionId) {
        String url = getAccountUService() + "/currentUser?sessionId=" + sessionId;
        return webClient.get().uri(url).retrieve().bodyToMono(UserDto.class).block();

    }

    public String getAccountUService() {
        return eurekaClient.getApplication(ACCOUNT_SERVICE).getInstances().get(0).getHomePageUrl();
    }

}
