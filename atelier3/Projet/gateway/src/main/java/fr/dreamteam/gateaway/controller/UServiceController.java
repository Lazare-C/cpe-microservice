package fr.dreamteam.gateaway.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import fr.dreamteam.gateaway.service.UService;

import java.io.IOException;

@RestController
public class UServiceController {

    private final UService uService;

    public UServiceController(UService uService) {
        this.uService = uService;
    }

    @RequestMapping(value = {"/account/**"})
    public ResponseEntity<String> account(@RequestBody(required = false) String body) throws IOException {
        if (body == null) { body = ""; }
        return uService.proxy("ACCOUNT", body);
    }

    @RequestMapping(value = {"/card/**"})
    public ResponseEntity<String> card(@RequestBody(required = false) String body) throws IOException {
        if (body == null) { body = ""; }
        return uService.proxy("CARD", body);
    }

}
