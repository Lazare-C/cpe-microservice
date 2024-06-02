package fr.dreamteam.uservices.orchestrator.delegate;

import dto.CardDto;
import dto.CardOwner;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class CardOwnerIdUpdateAdapter implements JavaDelegate {

    private final WebClient webClient;
    @Value("${gateway.url}")
    private String gatewayUrl;

    public CardOwnerIdUpdateAdapter() {
        this.webClient = WebClient.create();
    }


    @Override
    public void execute(DelegateExecution execution) throws Exception {
        Long cardId = (Long) execution.getVariable("cardId");
        Long ownerId = (Long) execution.getVariable("userId");

        CardOwner cardOwner = new CardOwner(cardId, ownerId);

        CardDto getOldUserId = webClient.get().uri(gatewayUrl + "/card/" + cardId).retrieve().bodyToMono(CardDto.class).block();
        execution.setVariable("oldUserId", getOldUserId.userBoId());

        ClientResponse response = webClient.post().uri(gatewayUrl + "/card/update/owner").contentType(MediaType.APPLICATION_JSON).bodyValue(cardOwner)
                .exchangeToMono(Mono::just).block();
 /*       if (response != null && response.statusCode().isError()) {
            throw new RuntimeException("Error while updating card owner");
        }*/
    }

}
