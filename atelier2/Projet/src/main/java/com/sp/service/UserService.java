package com.sp.service;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final CardService cardService;

    public UserService(CardService cardService) {
        this.cardService = cardService;
    }

    public void generateUserCards(String username) {
        cardService.createUserInitialCards(username);
    }

}
