package fr.dreamteam.uservices.orchestrator.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;

import dto.CardOwner;
import reactor.core.publisher.Mono;

@Service
public class rollbackCardOwnerIdUpdateAdapter implements JavaDelegate {

    private final WebClient webClient;
    @Value("${gateway.url}")
    private String gatewayUrl;

    public rollbackCardOwnerIdUpdateAdapter() {
        this.webClient = WebClient.create();
    }

    @Override
    public void execute(DelegateExecution execution) throws Exception {
       Long cardId = (Long) execution.getVariable("cardId");
       Long oldOwnerId = (Long) execution.getVariable("oldUserId"); 

        CardOwner cardOwner = new CardOwner(cardId, oldOwnerId);

        ClientResponse response = webClient.post().uri(gatewayUrl + "/update/owner").bodyValue(cardOwner)
                .exchangeToMono(Mono::just).block();
        if (response != null && response.statusCode().isError()) {
            throw new RuntimeException("Error while updating balance");
        }
       
    }
}
