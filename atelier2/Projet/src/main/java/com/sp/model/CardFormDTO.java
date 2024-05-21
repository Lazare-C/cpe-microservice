package com.sp.model;

public class CardFormDTO  {

	private String name;
	private String description;
	private String imgUrl;
	private String familly;
	private String affinity;
	private String Hp;
	private String energy;
	private int attack;
	private int defense;
	private String price;



	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public CardFormDTO() {
		this.name = "";
		this.description= "";
		this.imgUrl="";
		this.familly="";
		this.affinity="";
		this.Hp="";
		this.energy="";
		this.attack=0;
		this.defense=0;
		this.price="";
	}


	public CardFormDTO(String name, String description, String imgUrl, String familly, String affinity, String Hp, String energy, int attack, int defense, String price) {
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
	public String getPrice() {
		return this.price;
	}

	public void setPrice(String price) {
		this.price = price;
	}


}