package fr.dreamteam.card.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class CardManagerException extends RuntimeException{
    public CardManagerException(String message) {
        super(message);
    }
}
