package com.example.plateforme.Repository;

import com.example.plateforme.Entities.Notes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotesRepo extends JpaRepository<Notes,Long> {
}
