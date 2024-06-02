package fr.dreamteam.uservices.orchestrator.delegate;

import dto.BalanceUpdate;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

@Service
public class UserUpdateBalanceAdapter implements JavaDelegate {

    private final WebClient webClient;
    @Value("${gateway.url}")
    private String gatewayUrl;

    public UserUpdateBalanceAdapter() {
        this.webClient = WebClient.create();
    }

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        Long userId = (Long) execution.getVariable("userId");
        Double amount = (Double) execution.getVariable("amount");
        Long oldUserId = (Long) execution.getVariable("oldUserId");
        BigDecimal amountBigD = BigDecimal.valueOf(amount.doubleValue());
        BalanceUpdate balanceUpdateOldUser = new BalanceUpdate(oldUserId, amountBigD);
        amountBigD = amountBigD.negate();
        BalanceUpdate balanceUpdate = new BalanceUpdate(userId, amountBigD);

        ClientResponse response = webClient.post().uri(gatewayUrl + "/account/receive").bodyValue(balanceUpdate)
                .exchangeToMono(Mono::just).block();
        ClientResponse responseOldUser = webClient.post().uri(gatewayUrl + "/account/receive").bodyValue(balanceUpdateOldUser)
                .exchangeToMono(Mono::just).block();
        if (response != null && response.statusCode().isError() && responseOldUser != null && responseOldUser.statusCode().isError()) {
            throw new RuntimeException("Error while updating balance");
        }
    }

}
