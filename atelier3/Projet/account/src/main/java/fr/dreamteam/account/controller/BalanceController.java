package fr.dreamteam.account.controller;

import dto.BalanceUpdate;
import fr.dreamteam.account.service.BalanceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BalanceController {

    private final BalanceService balanceService;

    public BalanceController(BalanceService balanceService) {
        this.balanceService = balanceService;
    }

    //PRIVATE API
    @PostMapping("/receive")
    public ResponseEntity<String> receive(@RequestBody BalanceUpdate balanceUpdate) {
        return this.balanceService.updateBalance(balanceUpdate);
    }

}
