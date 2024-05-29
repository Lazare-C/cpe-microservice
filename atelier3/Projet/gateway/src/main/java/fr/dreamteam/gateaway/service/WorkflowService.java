package fr.dreamteam.gateaway.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class WorkflowService {

    private final WebClient webClient;

    public WorkflowService() {
        webClient = WebClient.create();
    }

    //TODO PAS BON
    public void buyCard(Long cardId) {
        webClient.post().uri("http://localhost:8080/card/buy/" + cardId).retrieve().bodyToMono(Void.class).block();
    }
}
