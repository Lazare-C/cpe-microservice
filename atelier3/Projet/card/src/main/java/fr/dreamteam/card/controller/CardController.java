package fr.dreamteam.card.controller;


import dto.CardDto;
import dto.CardOwner;
import fr.dreamteam.card.service.CardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class CardController {

    private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }


    @GetMapping("/sellList")
    public ResponseEntity<List<CardDto>> getCardsToSell() {
        return ResponseEntity.ok(this.cardService.cardToSell());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CardDto> getCardById(@PathVariable Long id) {
        return ResponseEntity.ok(this.cardService.getCardById(id));
    }

    @PostMapping("/sell")
    public ResponseEntity<CardDto> sellCard(@RequestParam(value = "id") Long id, @RequestParam(value = "price") BigDecimal price) {
        return this.cardService.sellCard(id, price);
    }

    @PostMapping("/update/owner")
    public ResponseEntity<String> updateOwner(@RequestBody CardOwner cardOwner) {
        return this.cardService.updateOwner(cardOwner);
    }

    @PostMapping("/initiate")
    public ResponseEntity<String> initiate(@RequestParam(name = "userId") Long userId) {
        return this.cardService.createUserInitialCards(userId);
    }

    @GetMapping("/my")
    public ResponseEntity<List<CardDto>> getMyCards() {
        return this.cardService.getMyCards();
    }


}
