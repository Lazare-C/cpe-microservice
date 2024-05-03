package com.sp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sp.model.Card;
import com.sp.model.CardFormDTO;

@Controller 
public class RequestCrt {
    
    @Autowired
      cardDAO cardDAO;

    @RequestMapping(value = { "/", "/index"}, method = RequestMethod.GET)
        public String view(Model model) {
        model.addAttribute("myCard",cardDAO.getRandomCard() );
        return "cardView";
    }

    @RequestMapping(value = { "/addCard"}, method = RequestMethod.GET)
        public String addcard(Model model) {
        	CardFormDTO cardForm = new CardFormDTO();
        	model.addAttribute("cardForm", cardForm);
        	return "cardForm";
        }

    @RequestMapping(value = { "/addCard"}, method = RequestMethod.POST)
    public String addCard(Model model, @ModelAttribute("cardForm") CardFormDTO cardForm) {
    Card c=cardDAO.addCard( cardForm.getName(), cardForm.getDescription(),  cardForm.getImgUrl(),  cardForm.getFamilly(),  cardForm.getAffinity(),  cardForm.getHp(), cardForm.getEnergy(),  cardForm.getAttack(),  cardForm.getDefense());
        model.addAttribute("myCard",c );
        return "cardView";
    }

}
