package fr.dreamteam.account.controller;

import dto.UserDto;
import fr.dreamteam.account.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getAccountById(Long id) {
        return accountService.getUserById(id);
    }
}
