package fr.dreamteam.game.service;

import dto.CardDto;
import dto.RoomDto;
import fr.dreamteam.game.bo.RoomBo;
import fr.dreamteam.game.exception.InvalidRoomStateException;
import fr.dreamteam.game.mapper.CardMapper;
import fr.dreamteam.game.repository.RoomRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class RoomService {
    private final RoomRepository roomRepository;
    private final CardService cardService;

    private final CardMapper cardMapper;

    public RoomService(RoomRepository repository, CardService cardService, CardMapper cardMapper){
        this.roomRepository = repository;
        this.cardService = cardService;
        this.cardMapper = cardMapper;
    }
    public List<RoomBo> getAllRooms(){
        return roomRepository.findAll();
    }
    public Optional<RoomBo> findById(Long id) {
        RoomBo room = this.roomRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Room not found"));
        return roomRepository.findById(id);
    }

    public RoomBo createRoom(RoomDto roomDto){

        RoomBo room = new RoomBo();
        room.setName(roomDto.name());
        room.setBet(roomDto.bet());

        return roomRepository.save(room);
    }

    public RoomBo updateRoom(Long id, RoomDto roomDTO) {
        RoomBo room = findById(id)
                .orElseThrow(() -> new InvalidRoomStateException("Room not found with id: " + id));

        if (roomDTO.name() != null) {
            room.setName(roomDTO.name());
        }

        if (roomDTO.cardId1() != null) {
            room.setCardId1(roomDTO.cardId1());
        }

        if (roomDTO.cardId2() != null) {
            room.setCardId2(roomDTO.cardId2());
        }

        return roomRepository.save(room);
    }

    public ResponseEntity<String> playGame(Long roomId){
        Optional<RoomBo> room = findById(roomId);

        if (room.get().getCardId1() == null || room.get().getCardId2() == null){
            throw new InvalidRoomStateException("Une ou plusieurs cartes dans la room sont manquantes");
        }

        return ResponseEntity.ok("success");
    }

    public ResponseEntity<String> attack(Long roomId, Long attackUserId){
        Optional<RoomBo> room = findById(roomId);

        CardDto card1 = cardService.getCardById(room.get().getCardId1());
        CardDto card2 = cardService.getCardById(room.get().getCardId2());

        if (card1.userBoId() == attackUserId){
            Random random = new Random();
            int randomInt = random.nextInt(10) + 1;
            this.cardMapper.toEntity(card2).setEnergy(card1.attack() * randomInt);
        }
        else {
            Random random = new Random();
            int randomInt = random.nextInt(10) + 1;
            this.cardMapper.toEntity(card1).setEnergy(card2.attack() * randomInt);
        }

        return ResponseEntity.ok("success");
    }
}

