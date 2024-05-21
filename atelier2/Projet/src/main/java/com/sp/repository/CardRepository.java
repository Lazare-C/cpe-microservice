package com.sp.repository;

import com.sp.model.Card;
import com.sp.model.bo.UserBo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {

    @Query("select c from Card c where c.userBo.id = ?1")
    List<Card> getUserCards(int userId);

    @Query("select c from Card c where c.price > 0")
    List<Card> getCardsToSell();
}
