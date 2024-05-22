package com.sp.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.sp.model.Card;

@Service
public class cardDAO {
	private List<Card> myCardList;
	private Random randomGenerator;

	public cardDAO() {
		myCardList=new ArrayList<>();
		randomGenerator = new Random();
		createCardList();
	}

	private void createCardList() {
  
		Card c1=new Card("Pickachu", "Etiam ac dolor ac tellus dignissim tristique et id elit. Quisque mattis est ex, at scelerisque sem maximus et. Sed egestas imperdiet arcu, in hendrerit risus venenatis nec. Nam ut velit vel sem rhoncus", "https://www.pokepedia.fr/images/thumb/7/76/Pikachu-DEPS.png/800px-Pikachu-DEPS.png", "Pokemon", "feu", "100" , "50", 50, 50);
		Card c2=new Card("Pickachu", "Etiam ac dolor ac tellus dignissim tristique et id elit. Quisque mattis est ex, at scelerisque sem maximus et. Sed egestas imperdiet arcu, in hendrerit risus venenatis nec. Nam ut velit vel sem rhoncus", "https://www.pokepedia.fr/images/thumb/7/76/Pikachu-DEPS.png/800px-Pikachu-DEPS.png", "Pokemon", "feu", "100" , "50", 50, 50);
		Card c3=new Card("Pickachu", "Etiam ac dolor ac tellus dignissim tristique et id elit. Quisque mattis est ex, at scelerisque sem maximus et. Sed egestas imperdiet arcu, in hendrerit risus venenatis nec. Nam ut velit vel sem rhoncus", "https://www.pokepedia.fr/images/thumb/7/76/Pikachu-DEPS.png/800px-Pikachu-DEPS.png", "Pokemon", "feu", "100" , "50", 50, 50);
		Card c4=new Card("Pickachu", "Etiam ac dolor ac tellus dignissim tristique et id elit. Quisque mattis est ex, at scelerisque sem maximus et. Sed egestas imperdiet arcu, in hendrerit risus venenatis nec. Nam ut velit vel sem rhoncus", "https://www.pokepedia.fr/images/thumb/7/76/Pikachu-DEPS.png/800px-Pikachu-DEPS.png", "Pokemon", "feu", "100" , "50", 50, 50);
		Card c5=new Card("Pickachu", "Etiam ac dolor ac tellus dignissim tristique et id elit. Quisque mattis est ex, at scelerisque sem maximus et. Sed egestas imperdiet arcu, in hendrerit risus venenatis nec. Nam ut velit vel sem rhoncus", "https://www.pokepedia.fr/images/thumb/7/76/Pikachu-DEPS.png/800px-Pikachu-DEPS.png", "Pokemon", "feu", "100" , "50", 50, 50);

		myCardList.add(c1);
		myCardList.add(c2);
		myCardList.add(c3);
		myCardList.add(c4);
		myCardList.add(c5);
	}
	public List<Card> getCardList() {
		return this.myCardList;
	}
	public Card getCardByName(String name){
		for (Card cardBean : myCardList) {
			if(cardBean.getName().equals(name)){
				return cardBean;
			}
		}
		return null;
	}
	public Card getRandomCard(){
		int index=randomGenerator.nextInt(this.myCardList.size());
		return this.myCardList.get(index);
	}

	public Card addCard(String name, String description, String imgUrl, String familly, String affinity, String Hp, String energy, int attack, int defense, BigDecimal price) {
		Card p=new Card( name,  description,  imgUrl,  familly,  affinity, Hp, energy, attack, defense, price);
		this.myCardList.add(p);
		return p;
	}
}

