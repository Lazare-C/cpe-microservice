package com.sp.service;

import com.sp.exception.CardManagerException;
import com.sp.exception.LoginException;
import com.sp.model.Card;
import com.sp.repository.CardRepository;
import com.sp.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;


import java.math.BigDecimal;
import java.util.List;

@Service
public class CardService {
    private final CardRepository cardRepository;
    private final AuthService authService;

    public CardService(CardRepository cardRepository, AuthService authService) {
        this.cardRepository = cardRepository;
        this.authService = authService;
    }

    public List<Card> getUserCards(Long userId){
        List<Card> userCardList = cardRepository.getUserCards(userId);
        if(userCardList.isEmpty()){
            throw new CardManagerException("User has no cards");
        }

        return userCardList;
    }

    public List<Card> cardToSell(){
        return cardRepository.getCardsToSell();
    }

    public void buyCard(Card card){
        if(authService.getUser().getBalance().compareTo(card.getPrice()) < 0){
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
        if(!card.getOwner().getId().equals(authService.getUser().getId())){
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
}
