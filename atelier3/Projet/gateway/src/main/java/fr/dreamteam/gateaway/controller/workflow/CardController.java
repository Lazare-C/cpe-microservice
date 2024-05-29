package fr.dreamteam.gateaway.controller.workflow;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CardController {


    @PostMapping(name = "/card/buy/{id}")
    public void buyCard(@PathVariable(name = "id") Long cardId) {

    }

}
