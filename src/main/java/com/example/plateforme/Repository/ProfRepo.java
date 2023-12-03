package com.example.plateforme.Repository;

import com.example.plateforme.Entities.Course;
import com.example.plateforme.Entities.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProfRepo extends JpaRepository<Professor, Long> {
	Optional<Professor> findByFullName(String fullName);
	Optional<Professor> findByEmail(String email);
    
}