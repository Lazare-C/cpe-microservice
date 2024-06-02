package fr.dreamteam.account.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class LoginException extends RuntimeException{
    public LoginException(String message) {
        super(message);
    }
}
