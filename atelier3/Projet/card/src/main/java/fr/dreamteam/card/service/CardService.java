package fr.dreamteam.card.service;

import dto.CardDto;
import dto.CardOwner;
import dto.UserDto;
import fr.dreamteam.card.bo.CardBo;
import fr.dreamteam.card.exception.CardManagerException;
import fr.dreamteam.card.mapper.CardMapper;
import fr.dreamteam.card.repository.CardRepository;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Service
public class CardService {
    private final CardRepository cardRepository;
    private final CardMapper cardMapper;
    private final UserService userService;
    private final HttpServletRequest httpServletRequest;

    public CardService(CardRepository cardRepository, CardMapper cardMapper, UserService userService, HttpServletRequest httpServletRequest) {
        this.cardRepository = cardRepository;
        this.userService = userService;
        this.cardMapper = cardMapper;
        this.httpServletRequest = httpServletRequest;
    }

    public List<CardBo> getUserCards(Long userId) {
        List<CardBo> userCardBoList = cardRepository.getUserCards(userId);
        if (userCardBoList.isEmpty()) {
            throw new CardManagerException("User has no cards");
        }

        return userCardBoList;
    }

    public ResponseEntity<String> createUserInitialCards(Long userId) {
        CardBo cardBo1 = new CardBo("Card1", "Card1 description", "https://i.imgur.com/1.jpg", "Familly1", "Affinity1", 100, 100, 10, 10, BigDecimal.ZERO);
        CardBo cardBo2 = new CardBo("Card2", "Card2 description", "https://i.imgur.com/2.jpg", "Familly2", "Affinity2", 200, 200, 20, 20, BigDecimal.ZERO);
        CardBo cardBo3 = new CardBo("Card3", "Card3 description", "https://i.imgur.com/3.jpg", "Familly3", "Affinity3", 300, 300, 30, 30, BigDecimal.ZERO);
        CardBo cardBo4 = new CardBo("Card4", "Card4 description", "https://i.imgur.com/4.jpg", "Familly4", "Affinity4", 400, 400, 40, 40, BigDecimal.ZERO);
        CardBo cardBo5 = new CardBo("Card5", "Card5 description", "https://i.imgur.com/5.jpg", "Familly5", "Affinity5", 500, 500, 50, 50, BigDecimal.ZERO);
        cardBo1.setOwnerId(userId);
        cardBo2.setOwnerId(userId);
        cardBo3.setOwnerId(userId);
        cardBo4.setOwnerId(userId);
        cardBo5.setOwnerId(userId);
        List<CardBo> cards = List.of(cardBo1, cardBo2, cardBo3, cardBo4, cardBo5);
        cardRepository.saveAll(cards);
        return ResponseEntity.ok("success");
    }


    public List<CardDto> cardToSell() {
        return this.cardMapper.toDtoList(cardRepository.getCardsToSell());
    }


    public ResponseEntity<CardDto> sellCard(Long cardId, BigDecimal price) {
        CardBo cardBo = this.cardRepository.findById(cardId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Card not found"));

        UserDto currentUser = this.userService.getCurrentUser();
        if (currentUser == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
        if (!currentUser.id().equals(cardBo.getOwnerId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User does not own the card");
        }

        if (price.compareTo(BigDecimal.ZERO) < 0) {
            throw new CardManagerException("Price must be positive");
        }
        cardBo.setPrice(price);
        cardBo = cardRepository.saveAndFlush(cardBo);
        return ResponseEntity.ok(this.cardMapper.toDto(cardBo));
    }

    public CardDto getCardById(Long id) {
        CardBo cardBo = this.cardRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Card not found"));
        return this.cardMapper.toDto(cardBo);
    }

    public ResponseEntity<String> updateOwner(CardOwner cardOwner) {
        CardBo cardBo = this.cardRepository.findById(cardOwner.cardId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Card not found"));

        UserDto user = this.userService.getUserById(cardOwner.ownerId());
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
        if (user.balance().compareTo(cardBo.getPrice()) < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User does not have enough money");
        }
        if (cardOwner.ownerId().equals(cardBo.getOwnerId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User already owns the card");
        }
        if (cardBo.getPrice().compareTo(BigDecimal.ZERO) == 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "CardBo is not for sale");
        }
        cardBo.setPrice(BigDecimal.ZERO);
        cardBo.setOwnerId(cardOwner.ownerId());
        this.cardRepository.save(cardBo);
        return ResponseEntity.ok("success");
    }

    public ResponseEntity<List<CardDto>> getMyCards() {
        String sessionId = Arrays.stream(httpServletRequest.getCookies())
                .filter(cookie -> cookie.getName().equals("sessionId"))
                .findFirst().orElseThrow(() -> new RuntimeException("No session cookie found")).getValue();
        UserDto currentUser = this.userService.getUser(sessionId);        if (currentUser == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
        List<CardBo> cards = this.cardRepository.getUserCards(currentUser.id());
        return ResponseEntity.ok(this.cardMapper.toDtoList(cards));
    }
}
