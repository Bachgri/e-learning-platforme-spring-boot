package com.example.plateforme.Repository;

import com.example.plateforme.Entities.App_User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface RepoUser extends JpaRepository<App_User, Long> {
    Optional<App_User> findByEmail(String email);


}
