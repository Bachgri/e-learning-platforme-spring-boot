package com.example.plateforme.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String image;
    private String name;
    private String description;
    @ManyToOne() 
 
    private Professor professor;

    @OneToMany(mappedBy = "course")
    //@JoinColumn(name = "course_id")
    private List<Chapter> chapters = new ArrayList() ;
    
    @OneToOne(cascade = CascadeType.ALL)
    private Exam exam;
    
}
