package com.sp.service;

import com.sp.exception.CardManagerException;
import com.sp.exception.LoginException;
import com.sp.model.Card;
import com.sp.model.bo.UserBo;
import com.sp.repository.CardRepository;
import com.sp.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;


import java.math.BigDecimal;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

@Service
public class CardService implements Observer {
    private final CardRepository cardRepository;
    private final AuthService authService;
    private final UserRepository userRepository;

    public CardService(CardRepository cardRepository, AuthService authService, UserRepository userRepository) {
        this.cardRepository = cardRepository;
        this.authService = authService;
        this.userRepository = userRepository;
        this.authService.addObserver(this);
    }

    public List<Card> getUserCards(Long userId){
        List<Card> userCardList = cardRepository.getUserCards(userId);
        if(userCardList.isEmpty()){
            throw new CardManagerException("User has no cards");
        }

        return userCardList;
    }

    public void createUserInitialCards(String username){
        UserBo user = userRepository.findByUsername(username);
        Card card1 = new Card("Card1", "Card1 description", "https://i.imgur.com/1.jpg", "Familly1", "Affinity1", "100", "100", 10, 10, BigDecimal.ZERO);
        Card card2 = new Card("Card2", "Card2 description", "https://i.imgur.com/2.jpg", "Familly2", "Affinity2", "200", "200", 20, 20, BigDecimal.ZERO);
        Card card3 = new Card("Card3", "Card3 description", "https://i.imgur.com/3.jpg", "Familly3", "Affinity3", "300", "300", 30, 30, BigDecimal.ZERO);
        Card card4 = new Card("Card4", "Card4 description", "https://i.imgur.com/4.jpg", "Familly4", "Affinity4", "400", "400", 40, 40, BigDecimal.ZERO);
        Card card5 = new Card("Card5", "Card5 description", "https://i.imgur.com/5.jpg", "Familly5", "Affinity5", "500", "500", 50, 50, BigDecimal.ZERO);
        card1.setOwner(user);
        card2.setOwner(user);
        card3.setOwner(user);
        card4.setOwner(user);
        card5.setOwner(user);
        cardRepository.save(card1);
        cardRepository.save(card2);
        cardRepository.save(card3);
        cardRepository.save(card4);
        cardRepository.save(card5);
    }

    public List<Card> cardToSell(){
        return cardRepository.getCardsToSell();
    }

    public void buyCard(Card card){
        if(authService.getUser() == null) {
            throw new LoginException("User not logged in");
        }
        else if(authService.getUser().getBalance().compareTo(card.getPrice()) < 0){
            throw new CardManagerException("Not enough money");
        } else if(card.getOwner().getId().equals(authService.getUser().getId())){
            throw new CardManagerException("You already own this card");
        } else if(card.getPrice().compareTo(BigDecimal.ZERO) == 0){
            throw new CardManagerException("Card is not for sale");
        }
        authService.getUser().setBalance(authService.getUser().getBalance().subtract(card.getPrice()));
        card.getOwner().setBalance(card.getOwner().getBalance().add(card.getPrice()));
        card.setOwner(authService.getUser());
        cardRepository.save(card);
    }

    public void sellCard(Card card, BigDecimal price){
        if(authService.getUser() == null) {
            throw new LoginException("User not logged in");
        }
        else if(!card.getOwner().getId().equals(authService.getUser().getId())){
            throw new CardManagerException("You do not own this card");
        } else if (price.compareTo(BigDecimal.ZERO) < 0){
            throw new CardManagerException("Price must be positive");
        }
        card.setPrice(price);
        cardRepository.save(card);
    }

    public void addCard(Card card){
        cardRepository.save(card);
    }
    public Card addCard(String name, String description, String imgUrl, String familly, String affinity, String Hp, String energy, int attack, int defense, BigDecimal price) {
        Card p=new Card( name,  description,  imgUrl,  familly,  affinity, Hp, energy, attack, defense, price);
        cardRepository.save(p);
        return p;
    }

    @Override
    public void update(Observable o, Object arg) {
        if(arg instanceof String){
            createUserInitialCards((String) arg);
        }
    }
}
