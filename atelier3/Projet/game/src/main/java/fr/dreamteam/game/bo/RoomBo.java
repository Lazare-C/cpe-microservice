package fr.dreamteam.game.bo;

import jakarta.persistence.*;

@Entity
@Table(name = "room")
public class RoomBo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "bet", nullable = false)
    private Integer bet;
    @Column(name = "cardId", nullable = true)
    private Long cardId1;
    @Column(name = "cardId", nullable = true)
    private Long cardId2;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getBet(){
        return bet;
    }
    public void setBet(Integer bet) {
        this.bet = bet;
    }
    public void setCardId1(Long cardId1){
        this.cardId1 = cardId1;
    }
    public Long getCardId1(){
        return cardId1;
    }
    public void setCardId2(Long cardId2){
        this.cardId2 = cardId2;
    }
    public Long getCardId2(){
        return cardId2;
    }
}
