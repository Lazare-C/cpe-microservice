package fr.dreamteam.card.service;

import dto.CardDto;
import dto.CardOwner;
import dto.UserDto;
import fr.dreamteam.card.bo.CardBo;
import fr.dreamteam.card.exception.CardManagerException;
import fr.dreamteam.card.mapper.CardMapper;
import fr.dreamteam.card.repository.CardRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.security.auth.login.LoginException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Optional;

@Service
public class CardService implements Observer {
    private final CardRepository cardRepository;
    private final AuthService authService;
    private final UserRepository userRepository;
    private final CardMapper cardMapper;
    private final UserService userService;

    public CardService(CardRepository cardRepository, AuthService authService, UserRepository userRepository, CardMapper cardMapper, UserService userService) {
        this.cardRepository = cardRepository;
        this.authService = authService;
        this.userRepository = userRepository;
        this.userService = userService;
        this.authService.addObserver(this);
        this.cardMapper = cardMapper;
    }

    public List<CardBo> getUserCards(Long userId) {
        List<CardBo> userCardBoList = cardRepository.getUserCards(userId);
        if (userCardBoList.isEmpty()) {
            throw new CardManagerException("User has no cards");
        }

        return userCardBoList;
    }

    public void createUserInitialCards(String username) {
        UserBo user = userRepository.findByUsername(username);
        CardBo cardBo1 = new CardBo("Card1", "Card1 description", "https://i.imgur.com/1.jpg", "Familly1", "Affinity1", 100, 100, 10, 10, BigDecimal.ZERO);
        CardBo cardBo2 = new CardBo("Card2", "Card2 description", "https://i.imgur.com/2.jpg", "Familly2", "Affinity2", 200, 200, 20, 20, BigDecimal.ZERO);
        CardBo cardBo3 = new CardBo("Card3", "Card3 description", "https://i.imgur.com/3.jpg", "Familly3", "Affinity3", 300, 300, 30, 30, BigDecimal.ZERO);
        CardBo cardBo4 = new CardBo("Card4", "Card4 description", "https://i.imgur.com/4.jpg", "Familly4", "Affinity4", 400, 400, 40, 40, BigDecimal.ZERO);
        CardBo cardBo5 = new CardBo("Card5", "Card5 description", "https://i.imgur.com/5.jpg", "Familly5", "Affinity5", 500, 500, 50, 50, BigDecimal.ZERO);
        cardBo1.setOwner(user);
        cardBo2.setOwner(user);
        cardBo3.setOwner(user);
        cardBo4.setOwner(user);
        cardBo5.setOwner(user);
        cardRepository.save(cardBo1);
        cardRepository.save(cardBo2);
        cardRepository.save(cardBo3);
        cardRepository.save(cardBo4);
        cardRepository.save(cardBo5);
    }

    public List<CardDto> cardToSell() {
        return this.cardMapper.toDtoList(cardRepository.getCardsToSell());
    }

    public CardDto buyCard(Long cardId) {
        CardBo cardBo = this.cardRepository.findById(cardId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Card not found"));
        if (authService.getUser() == null) {
            throw new LoginException("User not logged in");
        } else if (authService.getUser().getBalance().compareTo(cardBo.getPrice()) < 0) {
            throw new CardManagerException("Not enough money");
        } else if (cardBo.getOwner().getId().equals(authService.getUser().getId())) {
            throw new CardManagerException("You already own this cardBo");
        } else if (cardBo.getPrice().compareTo(BigDecimal.ZERO) == 0) {
            throw new CardManagerException("CardBo is not for sale");
        }
        authService.getUser().setBalance(authService.getUser().getBalance().subtract(cardBo.getPrice()));
        cardBo.getOwner().setBalance(cardBo.getOwner().getBalance().add(cardBo.getPrice()));
        cardBo.setPrice(BigDecimal.ZERO);
        cardBo.setOwner(authService.getUser());
        cardBo = cardRepository.saveAndFlush(cardBo);
        return this.cardMapper.toDto(cardBo);
    }

    public CardDto sellCard(Long cardId, BigDecimal price) {
        CardBo cardBo = this.cardRepository.findById(cardId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Card not found"));

        if (authService.getUser() == null) {
            throw new LoginException("User not logged in");
        } else if (!cardBo.getOwner().getId().equals(authService.getUser().getId())) {
            throw new CardManagerException("You do not own this cardBo");
        } else if (price.compareTo(BigDecimal.ZERO) < 0) {
            throw new CardManagerException("Price must be positive");
        }
        cardBo.setPrice(price);
        cardBo = cardRepository.saveAndFlush(cardBo);
        return this.cardMapper.toDto(cardBo);
    }

    public void addCard(CardBo cardBo) {
        cardRepository.save(cardBo);
    }

    public CardBo addCard(String name, String description, String imgUrl, String familly, String affinity, int hp, int energy, int attack, int defense, BigDecimal price) {
        CardBo p = new CardBo(name, description, imgUrl, familly, affinity, hp, energy, attack, defense, price);
        cardRepository.save(p);
        return p;
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof String) {
            createUserInitialCards((String) arg);
        }
    }

    public Optional<CardBo> findById(Long id) {
        return cardRepository.findById(id);
    }

    public List<CardDto> getMyCards() {
        UserBo userBo = this.authService.getUserOptional()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        List<CardBo> cards = this.getUserCards(userBo.getId());
        return cardMapper.toDtoList(cards);
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

        cardBo.setOwnerId(cardOwner.ownerId());
        this.cardRepository.save(cardBo);
        return ResponseEntity.ok("success");
    }
}
