package dto;

import java.io.Serializable;
import java.math.BigDecimal;

public record UserDto(Long id, String username, BigDecimal balance) implements Serializable {
}