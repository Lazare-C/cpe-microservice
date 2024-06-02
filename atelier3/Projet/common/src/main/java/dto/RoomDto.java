package dto;

import java.io.Serializable;

public record RoomDto(Long id, String name, Integer bet, Long cardId1, Long cardId2) implements Serializable {
}
