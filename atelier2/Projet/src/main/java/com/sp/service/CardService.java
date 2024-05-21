package com.sp.service;

import com.sp.exception.CardManagerException;
import com.sp.exception.LoginException;
import com.sp.model.Card;
import com.sp.repository.CardRepository;
import com.sp.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;



import java.util.List;

@Service
public class CardService {
    private final CardRepository cardRepository;

    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public List<Card> getUserCards(int userId){
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
}
