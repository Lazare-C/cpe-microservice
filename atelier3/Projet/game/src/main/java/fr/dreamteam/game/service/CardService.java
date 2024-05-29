package fr.dreamteam.game.service;

import com.netflix.discovery.EurekaClient;
import dto.CardDto;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class CardService {
    private static final String CARD_SERVICE = "CARD";
    private final WebClient webClient;
    private final EurekaClient eurekaClient;

    public CardService(EurekaClient eurekaClient) {
        this.eurekaClient = eurekaClient;
        webClient = WebClient.create();
    }

    public CardDto getCardById(Long cardId) {
        return webClient.get().uri(getCardUService() + "/" + cardId).retrieve().bodyToMono(CardDto.class).block();
    }

    public String getCardUService() {
        return eurekaClient.getApplication(CARD_SERVICE).getInstances().get(0).getHomePageUrl();
    }
}
