package fr.dreamteam.card.service;


import com.netflix.discovery.EurekaClient;
import dto.UserDto;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class UserService {

    private static final String ACCOUNT_SERVICE = "ACCOUNT";
    private final WebClient webClient;
    private final EurekaClient eurekaClient;


    public UserService(EurekaClient eurekaClient) {
        this.eurekaClient = eurekaClient;
        webClient = WebClient.create();
    }

    public UserDto getUserById(Long userId) {
        return webClient.get().uri(getAccountUService() + "/" + userId).retrieve().bodyToMono(UserDto.class).block();
    }


    public String getAccountUService() {
        return eurekaClient.getApplication(ACCOUNT_SERVICE).getInstances().get(0).getHomePageUrl();
    }

}
