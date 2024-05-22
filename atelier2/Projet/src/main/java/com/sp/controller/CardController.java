package com.sp.controller;

import com.sp.model.CardDto;
import com.sp.service.CardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CardController {

    private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping("/mycards")
    public ResponseEntity<List<CardDto>> getMyCards() {
        return ResponseEntity.ok(this.cardService.getMyCards());
    }

    @GetMapping("/card/{id}")
    public ResponseEntity<CardDto> getCardById(@PathVariable Long id) {
        return ResponseEntity.ok(this.cardService.getCardById(id));
    }

}
