package com.minigame.apiminigame.Repository;

import com.minigame.apiminigame.Model.UserAuthModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserAuthRepository extends JpaRepository<UserAuthModel, Long> {
    Optional<UserAuthModel> findByUsername(String username);
}
