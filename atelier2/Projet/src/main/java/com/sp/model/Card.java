package com.sp.model;
import java.util.Objects;
public class Card  {
	private int userId;

	private String name;
	private String description;
	private String imgUrl;
	private String familly;
	private String affinity;
	private String Hp;
	private String energy;
	private String attack;
	private String defense;


	public Card() {
	}

	public Card(String name, String description, String imgUrl, String familly, String affinity, String Hp, String energy, String attack, String defense) {
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

	public String getAttack() {
		return this.attack;
	}

	public void setAttack(String attack) {
		this.attack = attack;
	}

	public String getDefense() {
		return this.defense;
	}

	public void setDefense(String defense) {
		this.defense = defense;
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

	public Card attack(String attack) {
		setAttack(attack);
		return this;
	}

	public Card defense(String defense) {
		setDefense(defense);
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
		return Objects.hash(name, description, imgUrl, familly, affinity, Hp, energy, attack, defense);
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
			"}";
	}
	
  
}
