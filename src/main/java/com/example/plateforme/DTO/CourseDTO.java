package com.example.plateforme.DTO;

import com.example.plateforme.Entities.Chapter;
import org.w3c.dom.Text;

import java.util.List;
import java.util.UUID;

public record CourseDTO(Long id,
                        String name,
                        String image,
                        List<Chapter> chapters,
                        String professor) {
}
