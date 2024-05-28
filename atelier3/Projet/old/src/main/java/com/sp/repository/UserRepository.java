package com.sp.repository;

import com.sp.bo.UserBo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserBo, Long> {
    @Query("select u from UserBo u where u.username = ?1")
    UserBo findByUsername(String username);



}
