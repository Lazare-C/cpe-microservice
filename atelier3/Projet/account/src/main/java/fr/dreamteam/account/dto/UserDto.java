package fr.dreamteam.account.dto;

import fr.dreamteam.account.bo.UserBo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * DTO for {@link UserBo}
 */
public record UserDto(Long id, String username, BigDecimal balance) implements Serializable {
}