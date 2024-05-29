package fr.dreamteam.card.controller;


import dto.CardDto;
import dto.CardOwner;
import fr.dreamteam.card.service.CardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/card")
public class CardController {

    private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping("/my")
    public ResponseEntity<List<CardDto>> getMyCards() {
        return ResponseEntity.ok(this.cardService.getMyCards());
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
        return ResponseEntity.ok(this.cardService.sellCard(id, price));
    }

    @PostMapping("/buy")
    public ResponseEntity<CardDto> buyCard(@RequestParam(value = "id") Long id) {
        return ResponseEntity.ok(this.cardService.buyCard(id));
    }


    @PostMapping("/update/owner")
    public ResponseEntity<String> updateOwner(@RequestBody CardOwner cardOwner) {
        return this.cardService.updateOwner(cardOwner);
    }


}
