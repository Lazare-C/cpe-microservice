package fr.dreamteam.gateaway.controller;

import fr.dreamteam.gateaway.service.WorkflowService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CardController {

    private final WorkflowService workflowService;

    public CardController(WorkflowService workflowService) {
        this.workflowService = workflowService;
    }

    @PostMapping("/wf/card/buy/{id}")
    public void buyCard(@PathVariable(name = "id") Long cardId) {
        workflowService.buyCard(cardId);
    }

}
