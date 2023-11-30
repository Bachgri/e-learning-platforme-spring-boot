package com.example.plateforme.Repository;

import com.example.plateforme.Entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RepoRole extends JpaRepository<Role,Long> {
    Optional<Role> findByName(String name);
}
