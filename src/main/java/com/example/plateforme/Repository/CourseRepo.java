package com.example.plateforme.Repository;

import com.example.plateforme.Entities.Course;
import com.example.plateforme.Entities.Professor;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CourseRepo extends JpaRepository<Course, Long> {
    Optional<Course> findByName(String name);
    void deleteByName(String name);
    public List<Course> findByProfessor(Professor p);
}
