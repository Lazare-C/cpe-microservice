package fr.dreamteam.card;

import dto.CardDto;
import dto.CardOwner;
import dto.UserDto;
import fr.dreamteam.card.bo.CardBo;
import fr.dreamteam.card.exception.CardManagerException;
import fr.dreamteam.card.mapper.CardMapper;
import fr.dreamteam.card.repository.CardRepository;
import fr.dreamteam.card.service.CardService;
import fr.dreamteam.card.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CardApplicationTests {
    @Mock
    private CardMapper cardMapper;

    @Mock
    private CardRepository cardRepository;
    @Mock
    private CardDto cardDto;

    @Mock
    private UserService userService;

    @InjectMocks
    private CardService cardService;

    @Test
    public void getUserCards_UserHasNoCards_ThrowsCardManagerException() {
        Long userId = 1L;
        when(cardRepository.getUserCards(userId)).thenReturn(List.of());

        assertThrows(CardManagerException.class, () -> cardService.getUserCards(userId));
        try{
            cardService.getUserCards(userId);
        } catch (CardManagerException e){
            assertEquals("User has no cards", e.getMessage());
        }
    }

    @Test
    public void getUserCards_UserHasCards_ReturnsCards() {
        Long userId = 1L;
        List<CardBo> userCards = List.of(new CardBo());
        when(cardRepository.getUserCards(userId)).thenReturn(userCards);
        List<CardBo> result = cardService.getUserCards(userId);
        assertEquals(userCards, result);
    }

    @Test
    public void createUserInitialCards_Success() {
        Long userId = 1L;

        ResponseEntity<String> response = cardService.createUserInitialCards(userId);

        assertEquals("success", response.getBody());
        verify(cardRepository, times(1)).saveAll(anyList());
    }

    @Test
    public void sellCard_PriceNegative_ThrowsCardManagerException() {
        Long cardId = 1L;
        BigDecimal price = BigDecimal.valueOf(-1);
        Long ownerId = 1L;
        CardBo cardBo = new CardBo();
        cardBo.setOwnerId(ownerId);
        when(cardRepository.findById(cardId)).thenReturn(Optional.of(cardBo));
        UserDto user = new UserDto(ownerId, "username", BigDecimal.ZERO);
        when(userService.getCurrentUser()).thenReturn(user);

        assertThrows(CardManagerException.class, () -> cardService.sellCard(cardId, price));
        try{
            cardService.sellCard(cardId, price);
        }catch(CardManagerException e){
            assertEquals("Price must be positive", e.getMessage());
        }
    }
    @Test
    public void getMyCards_Success() {
        Long userId = 1L;
        UserDto user = new UserDto(userId, "username", BigDecimal.ZERO);
        when(userService.getCurrentUser()).thenReturn(user);
        List<CardBo> cards = List.of(new CardBo());
        when(cardRepository.getUserCards(userId)).thenReturn(cards);
        List<CardDto> cardDtos = List.of(cardDto);
        when(cardMapper.toDtoList(cards)).thenReturn(cardDtos);

        ResponseEntity<List<CardDto>> response = cardService.getMyCards();

        assertEquals(cardDtos, response.getBody());
    }

    @Test
    public void updateOwner_Success() {
        Long cardId = 1L;
        Long userId = 1L;
        CardOwner cardOwner = new CardOwner(cardId, userId);
        CardBo cardBo = new CardBo();
        cardBo.setPrice(BigDecimal.TEN);
        when(cardRepository.findById(cardId)).thenReturn(Optional.of(cardBo));
        UserDto user = new UserDto(userId, "username", BigDecimal.TEN);
        when(userService.getUserById(userId)).thenReturn(user);

        ResponseEntity<String> response = cardService.updateOwner(cardOwner);

        assertEquals("success", response.getBody());
        verify(cardRepository, times(1)).save(cardBo);
    }
}
