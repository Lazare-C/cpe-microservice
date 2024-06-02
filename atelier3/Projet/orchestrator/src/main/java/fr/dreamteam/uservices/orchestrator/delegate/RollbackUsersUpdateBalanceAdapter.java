package fr.dreamteam.uservices.orchestrator.delegate;

import java.math.BigDecimal;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;

import dto.BalanceUpdate;
import reactor.core.publisher.Mono;

@Service
public class RollbackUsersUpdateBalanceAdapter implements JavaDelegate {

    private final WebClient webClient;
    @Value("${gateway.url}")
    private String gatewayUrl;

    public RollbackUsersUpdateBalanceAdapter() {
        this.webClient = WebClient.create();
    }

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        Long userId = (Long) execution.getVariable("userId");
        BigDecimal amount = (BigDecimal) execution.getVariable("amount");

        BalanceUpdate balanceUpdate = new BalanceUpdate(userId, amount.negate());
        ClientResponse response = webClient.post().uri(gatewayUrl + "/balance").bodyValue(balanceUpdate)
                .exchangeToMono(Mono::just).block();
        if (response != null && response.statusCode().isError()) {
            throw new RuntimeException("Error while updating balance");
        }
        
    }
}
