package com.sp.controller;

import com.sp.bo.CardBo;
import com.sp.bo.UserBo;
import com.sp.model.CardFormDto;
import com.sp.service.AuthService;
import com.sp.service.CardService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController()
@RequestMapping("/old")
public class RequestController {

    private final CardService cardService;
    private final AuthService authService;

    public RequestController(CardService cardService, AuthService authService) {
        this.cardService = cardService;
        this.authService = authService;
    }

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String view(Model model) {
        UserBo currentUser = this.authService.getUser();
        if (currentUser != null) {
            model.addAttribute("myUser", currentUser);
            return "homePage";
        } else {
            return "addUser";
        }

    }

    @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
    public String login(Model model) {
        model.addAttribute("errorMessage", "");
        return "login";
    }

    @RequestMapping(value = {"/addCard"}, method = RequestMethod.GET)
    public String addcard(Model model) {
        CardFormDto cardForm = new CardFormDto();
        model.addAttribute("cardForm", cardForm);
        return "cardForm";
    }

    @RequestMapping(value = {"/addCard"}, method = RequestMethod.POST)
    public String addCard(Model model, @ModelAttribute("cardForm") CardFormDto cardForm) {
        CardBo c = cardService.addCard(cardForm.getName(), cardForm.getDescription(), cardForm.getImgUrl(), cardForm.getFamilly(), cardForm.getAffinity(), cardForm.getHp(), cardForm.getEnergy(), cardForm.getAttack(), cardForm.getDefense(), cardForm.getPrice());
        model.addAttribute("myCard", c);
        return "cardView";
    }

    @RequestMapping(value = {"/list"}, method = RequestMethod.GET)
    public String getList(Model model) {
        List<CardBo> list = cardService.getUserCards(authService.getUser().getId());
        model.addAttribute("myList", list);
        return "listView";
    }

    @GetMapping("/list/{id}")
    public String getPokemonDetails(@PathVariable("id") Long id, Model model) {
        Optional<CardBo> pokemonCard = cardService.findById(id);

        if (pokemonCard.isPresent()) {
            model.addAttribute("pokemonCard", pokemonCard.get());
            return "fragments/selectedCard :: selectedCard";  // Retourne le nom du template Thymeleaf
        } else {
            // Gérez le cas où la carte n'est pas trouvée, par exemple, en retournant une page d'erreur ou un message
            return "cardNotFound";  // Retourne une page indiquant que la carte n'a pas été trouvée
        }
    }

}
