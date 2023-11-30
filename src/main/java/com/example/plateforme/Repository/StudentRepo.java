package com.example.plateforme.Repository;

import com.example.plateforme.Entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface StudentRepo extends JpaRepository<Student, Long> {
    Optional<Student> findByFullName(String fullName);
    public List<Student> findByValider(boolean valider);
}