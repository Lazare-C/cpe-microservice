package fr.dreamteam.game.controller;

import dto.RoomDto;
import fr.dreamteam.game.bo.RoomBo;
import fr.dreamteam.game.exception.InvalidRoomStateException;
import fr.dreamteam.game.service.RoomService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rooms")
public class RoomController {
    private final RoomService roomService;
    public RoomController(RoomService service){
        this.roomService = service;
    }

    @PostMapping("/play")
    public ResponseEntity<String> playGame(@RequestParam(value = "roomId") Long roomId) {
        return this.roomService.playGame(roomId);
    }

    @PostMapping("/createRoom")
    public ResponseEntity<String> createRoom(@RequestBody RoomDto roomDTO) {
        try {
            RoomBo room = roomService.createRoom(roomDTO);
            return ResponseEntity.status(201).body("Room created with ID: " + room.getId());
        } catch (InvalidRoomStateException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @PostMapping("/attack")
    public ResponseEntity<String> attack(@RequestParam(value = "roomId") Long roomId, @RequestParam(value = "userId") Long userId) {
        return this.roomService.attack(roomId, userId);
    }
}
