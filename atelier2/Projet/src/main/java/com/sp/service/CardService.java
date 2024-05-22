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

    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
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
