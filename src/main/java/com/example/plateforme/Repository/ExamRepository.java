package com.example.plateforme.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.plateforme.Entities.Exam;

public interface ExamRepository extends JpaRepository<Exam, Long> 
{
}

