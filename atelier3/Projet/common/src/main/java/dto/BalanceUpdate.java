package dto;

import java.math.BigDecimal;

//Si nombre négatif, on retire de l'argent, sinon on en ajoute
public record BalanceUpdate(Long userId, BigDecimal amount) {
}
