package com.sp.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * DTO for {@link com.sp.bo.UserBo}
 */
public record UserDto(Long id, String username, BigDecimal balance) implements Serializable {
}