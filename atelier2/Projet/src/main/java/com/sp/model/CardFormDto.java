package com.sp.model;

import java.math.BigDecimal;

public class CardFormDto {
	private String name;
	private String description;
	private String imgUrl;
	private String familly;
	private String affinity;
	private int hp;
	private int energy;
	private int attack;
	private int defense;
	private BigDecimal price;



	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public CardFormDto() {
		this.name = "";
		this.description= "";
		this.imgUrl="";
		this.familly="";
		this.affinity="";
		this.hp = 0;
		this.energy= 0;
		this.attack=0;
		this.defense=0;
		this.price=null;
	}


	public CardFormDto(String name, String description, String imgUrl, String familly, String affinity, int hp, int energy, int attack, int defense, BigDecimal price, Long id) {
		this.name = name;
		this.description = description;
		this.imgUrl = imgUrl;
		this.familly = familly;
		this.affinity = affinity;
		this.hp = hp;
		this.energy = energy;
		this.attack = attack;
		this.defense = defense;
		this.price = price;
	}

    // GETTER AND SETTER


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

	public int getHp() {
		return this.hp;
	}

	public void setHp(int Hp) {
		this.hp = Hp;
	}

	public int getEnergy() {
		return this.energy;
	}

	public void setEnergy(int energy) {
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
}