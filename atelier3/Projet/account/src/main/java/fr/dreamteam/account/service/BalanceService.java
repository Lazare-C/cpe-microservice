package fr.dreamteam.account.service;

import dto.BalanceUpdate;
import fr.dreamteam.account.bo.UserBo;
import fr.dreamteam.account.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class BalanceService {


    private final UserRepository userRepository;

    public BalanceService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ResponseEntity<String> updateBalance(BalanceUpdate balanceUpdate) {
        UserBo userBo = this.userRepository.findById(balanceUpdate.userId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        userBo.setBalance(userBo.getBalance().add(balanceUpdate.amount()));
        this.userRepository.save(userBo);
        return ResponseEntity.ok("success");
    }
}
