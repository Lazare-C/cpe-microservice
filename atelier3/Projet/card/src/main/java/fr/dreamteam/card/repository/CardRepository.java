package fr.dreamteam.card.repository;

import fr.dreamteam.card.bo.CardBo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepository extends JpaRepository<CardBo, Long> {

    @Query("select c from CardBo c where c.ownerId = ?1")
    List<CardBo> getUserCards(Long userId);

    @Query("select c from CardBo c where c.price > 0")
    List<CardBo> getCardsToSell();
}
