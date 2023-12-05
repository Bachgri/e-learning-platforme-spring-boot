package com.example.plateforme.Entities;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity @Data @AllArgsConstructor @NoArgsConstructor

public class Question {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
    private String label;
    
//    @ManyToOne(cascade = CascadeType.ALL)
//    private Response responseOk;
    
    @JsonProperty(access = Access.WRITE_ONLY)	
    @ManyToOne(cascade = CascadeType.MERGE)		
    private Exam exam;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "question")
    private List<Response> responses;
    
    private String note;
    
}	
	