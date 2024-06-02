package fr.dreamteam.card.bo;

import dto.UserDto;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "card")
public class CardBo {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "description", nullable = false)
    private String description;
    @Column(name = "imgUrl", nullable = true)
    private String imgUrl;
    @Column(name = "familly", nullable = false)
    private String familly;
    @Column(name = "affinity", nullable = false)
    private String affinity;
    @Column(name = "hp", nullable = false)
    private Integer hp;
    @Column(name = "energy", nullable = false)
    private Integer energy;
    @Column(name = "attack", nullable = false)
    private Integer attack;
    @Column(name = "defense", nullable = false)
    private int defense;
    @Column(name = "price", nullable = true)
    private BigDecimal price;
    @Column(name = "userId", nullable = true)
    private Long ownerId;



    public CardBo() {
    }

    public CardBo(String name, String description, String imgUrl, String familly, String affinity, int Hp, int energy, int attack, int defense, BigDecimal price) {
        this.name = name;
        this.description = description;
        this.imgUrl = imgUrl;
        this.familly = familly;
        this.affinity = affinity;
        this.hp = Hp;
        this.energy = energy;
        this.attack = attack;
        this.defense = defense;
        this.price = price;
    }

    public CardBo(String name, String description, String imgUrl, String familly, String affinity, int hp, int energy, int attack, int defense) {
        this.name = name;
        this.description = description;
        this.imgUrl = imgUrl;
        this.familly = familly;
        this.affinity = affinity;
        this.hp = hp;
        this.energy = energy;
        this.attack = attack;
        this.defense = defense;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return this.id;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImgUrl() {
        return this.imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getFamilly() {
        return this.familly;
    }

    public void setFamilly(String familly) {
        this.familly = familly;
    }

    public String getAffinity() {
        return this.affinity;
    }

    public void setAffinity(String affinity) {
        this.affinity = affinity;
    }

    public Integer getHp() {
        return this.hp;
    }

    public void setHp(Integer Hp) {
        this.hp = Hp;
    }

    public Integer getEnergy() {
        return this.energy;
    }

    public void setEnergy(Integer energy) {
        this.energy = energy;
    }

    public int getAttack() {
        return this.attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense() {
        return this.defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public CardBo name(String name) {
        setName(name);
        return this;
    }

    public CardBo description(String description) {
        setDescription(description);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof CardBo)) {
            return false;
        }
        CardBo poney = (CardBo) o;
        return Objects.equals(name, poney.name) && Objects.equals(description, poney.description) && Objects.equals(imgUrl, poney.imgUrl) && Objects.equals(familly, poney.familly) && Objects.equals(affinity, poney.affinity) && Objects.equals(hp, poney.hp) && Objects.equals(energy, poney.energy) && Objects.equals(attack, poney.attack) && Objects.equals(defense, poney.defense);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, imgUrl, familly, affinity, hp, energy, attack, defense, price);
    }

    @Override
    public String toString() {
        return "{" + " name='" + getName() + "'" + ", description='" + getDescription() + "'" + ", imgUrl='" + getImgUrl() + "'" + ", familly='" + getFamilly() + "'" + ", affinity='" + getAffinity() + "'" + ", Hp='" + getHp() + "'" + ", energy='" + getEnergy() + "'" + ", attack='" + getAttack() + "'" + ", defense='" + getDefense() + "'" + ", price='" + getPrice() + "'" + "}";
    }


}
