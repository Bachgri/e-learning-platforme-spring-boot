package com.example.plateforme.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.plateforme.Entities.Question;

public interface QuestionRepository extends JpaRepository<Question, Long> 
{
}
