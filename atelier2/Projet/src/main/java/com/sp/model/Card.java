package com.sp.model;
import com.sp.model.bo.UserBo;
import jakarta.persistence.*;
import org.apache.catalina.User;

import java.math.BigDecimal;
import java.util.Objects;
@Entity
@Table(name = "card")
public class Card  {


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
	private String Hp;
	@Column(name = "energy", nullable = false)
	private String energy;
	@Column(name = "attack", nullable = false)
	private int attack;
	@Column(name = "defense", nullable = false)
	private int defense;
	@Column(name = "price", nullable = true)
	private BigDecimal price;
	@ManyToOne
	@JoinColumn(name = "userId", nullable = true)
	private UserBo userBo;


	public Card() {
	}

	public Card(String name, String description, String imgUrl, String familly, String affinity, String Hp, String energy, int attack, int defense, BigDecimal price) {
		this.name = name;
		this.description = description;
		this.imgUrl = imgUrl;
		this.familly = familly;
		this.affinity = affinity;
		this.Hp = Hp;
		this.energy = energy;
		this.attack = attack;
		this.defense = defense;
		this.price = price;
	}

	public Card(String name, String description, String imgUrl, String familly, String affinity, String Hp, String energy, int attack, int defense) {
		this.name = name;
		this.description = description;
		this.imgUrl = imgUrl;
		this.familly = familly;
		this.affinity = affinity;
		this.Hp = Hp;
		this.energy = energy;
		this.attack = attack;
		this.defense = defense;
	}

	public String getName() {
		return this.name;
	}
	public Long getId() {
		return this.id;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getHp() {
		return this.Hp;
	}

	public void setHp(String Hp) {
		this.Hp = Hp;
	}

	public String getEnergy() {
		return this.energy;
	}

	public void setEnergy(String energy) {
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

	public void setOwner(UserBo userBo) {
		this.userBo = userBo;
	}

	public UserBo getOwner() {
		return userBo;
	}

	public Card name(String name) {
		setName(name);
		return this;
	}

	public Card description(String description) {
		setDescription(description);
		return this;
	}

	public Card imgUrl(String imgUrl) {
		setImgUrl(imgUrl);
		return this;
	}

	public Card familly(String familly) {
		setFamilly(familly);
		return this;
	}

	public Card affinity(String affinity) {
		setAffinity(affinity);
		return this;
	}

	public Card Hp(String Hp) {
		setHp(Hp);
		return this;
	}

	public Card energy(String energy) {
		setEnergy(energy);
		return this;
	}

	public Card attack(int attack) {
		setAttack(attack);
		return this;
	}

	public Card defense(int defense) {
		setDefense(defense);
		return this;
	}

	public Card price(BigDecimal price) {
		setPrice(price);
		return this;
	}

	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;
		if (!(o instanceof Card)) {
			return false;
		}
		Card poney = (Card) o;
		return Objects.equals(name, poney.name) && Objects.equals(description, poney.description) && Objects.equals(imgUrl, poney.imgUrl) && Objects.equals(familly, poney.familly) && Objects.equals(affinity, poney.affinity) && Objects.equals(Hp, poney.Hp) && Objects.equals(energy, poney.energy) && Objects.equals(attack, poney.attack) && Objects.equals(defense, poney.defense);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, description, imgUrl, familly, affinity, Hp, energy, attack, defense, price);
	}

	@Override
	public String toString() {
		return "{" +
			" name='" + getName() + "'" +
			", description='" + getDescription() + "'" +
			", imgUrl='" + getImgUrl() + "'" +
			", familly='" + getFamilly() + "'" +
			", affinity='" + getAffinity() + "'" +
			", Hp='" + getHp() + "'" +
			", energy='" + getEnergy() + "'" +
			", attack='" + getAttack() + "'" +
			", defense='" + getDefense() + "'" +
			", price='" + getPrice() + "'" +
			"}";
	}
	
  
}
