package com.sp.controller;

import com.sp.service.CardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sp.model.Card;
import com.sp.model.CardFormDTO;
import com.sp.model.bo.UserBo;
import com.sp.service.AuthService;

import java.util.List;

@Controller 
public class RequestCrt {

    private final CardService cardService;
    private final AuthService authService;

    public RequestCrt(CardService cardService, AuthService authService){
        this.cardService = cardService;
        this.authService = authService;
    }

    @RequestMapping(value = { "/", "/index"}, method = RequestMethod.GET)
        public String view(Model model) {
            UserBo currentUser = this.authService.getUser();
            if(currentUser != null){
                model.addAttribute("myUser", currentUser);
                return "homePage";
            } else {
                return "addUser";
            }

    }

    @RequestMapping(value = { "/login"}, method = RequestMethod.GET)
    public String login(Model model) {
        return "login";
    }

    @RequestMapping(value = { "/addCard"}, method = RequestMethod.GET)
        public String addcard(Model model) {
        	CardFormDTO cardForm = new CardFormDTO();
        	model.addAttribute("cardForm", cardForm);
        	return "cardForm";
        }

    @RequestMapping(value = { "/addCard"}, method = RequestMethod.POST)
    public String addCard(Model model, @ModelAttribute("cardForm") CardFormDTO cardForm) {
    Card c= cardService.addCard( cardForm.getName(), cardForm.getDescription(),  cardForm.getImgUrl(),  cardForm.getFamilly(),  cardForm.getAffinity(),  cardForm.getHp(), cardForm.getEnergy(),  cardForm.getAttack(),  cardForm.getDefense(), cardForm.getPrice());
        model.addAttribute("myCard",c );
        return "cardView";
    }

    @RequestMapping(value = { "/list"}, method = RequestMethod.GET)
    public String getList(Model model) {
        List<Card> list = cardService.getUserCards(authService.getUser().getId());
        model.addAttribute("myList",list );
        return "listView";
    }

}
