package fr.dreamteam.game.repository;

import fr.dreamteam.game.bo.RoomBo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<RoomBo, Long> {
}
