package fr.dreamteam.game.exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidRoomStateException extends RuntimeException{
    public InvalidRoomStateException(String message){
        super(message);
    }
}
