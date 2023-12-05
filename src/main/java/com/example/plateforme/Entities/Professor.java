package com.example.plateforme.Entities;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.ToString;

@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED) @ToString
public class Professor extends App_User{

    //private String specialite;
    @OneToMany(mappedBy = "professor", fetch = FetchType.EAGER)

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Course> cours = new ArrayList<>();
}
