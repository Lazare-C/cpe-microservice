package dto;

import java.io.Serializable;
import java.math.BigDecimal;


public record CardDto(Long id, String name, String description, String imgUrl, String familly, String affinity, int hp,
                      int energy, int attack, int defense, BigDecimal price, Long userBoId,
                      String userBoUsername) implements Serializable {
}