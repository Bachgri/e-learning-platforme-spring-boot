package com.example.plateforme.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.plateforme.Entities.Response;

public interface ResponseRepository extends JpaRepository<Response, Long>
{
}
