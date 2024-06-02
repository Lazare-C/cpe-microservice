package fr.dreamteam.account.service;

import dto.UserDto;
import fr.dreamteam.account.bo.UserBo;
import fr.dreamteam.account.mapper.UserMapper;
import fr.dreamteam.account.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AccountService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public AccountService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public ResponseEntity<UserDto> getUserById(Long userId) {
        UserBo userBo = this.userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        return ResponseEntity.ok(this.userMapper.toDto(userBo));
    }

}
