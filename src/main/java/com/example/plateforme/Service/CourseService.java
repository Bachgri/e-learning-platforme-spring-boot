package com.example.plateforme.Service;

import com.example.plateforme.DTO.CourseDTO; 
import com.example.plateforme.Entities.Course;
import com.example.plateforme.Entities.Professor;
import com.example.plateforme.Repository.CourseRepo;
import com.example.plateforme.Repository.ProfRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CourseService {

    @Autowired
    private CourseRepo courseRepo;

    @Autowired
    private ProfRepo profRepo;



    public Course get(Long id ){
        if(courseRepo.findById(id).isPresent())
            return courseRepo.findById(id).get();
        throw new RuntimeException();
    }


    public Course post(CourseDTO course){
        if(profRepo.findByFullName(course.professor()).isPresent()) {
            Professor prof = profRepo.findByFullName(course.professor()).get();
            Course cour = new Course();
            cour.setName(course.name());
            cour.setImage(course.image());
            cour.setProfessor(prof);

            cour.setChapters(course.chapters());

            return courseRepo.save(cour);
        }
        throw new RuntimeException();
    }

    public void delete(Long id){
        courseRepo.findById(id).get().setProfessor(null);
        courseRepo.deleteById(id);
    }
    public Course create(Course course){
    		return courseRepo.save(course);
    }
    public Course put(CourseDTO course){
        if(courseRepo.findById(course.id()).isPresent() && profRepo.findByFullName(course.professor()).isPresent()){
            Course cour = courseRepo.findById(course.id()).get();
            cour.setName(course.name());
            cour.setImage(course.image());
            cour.setChapters(course.chapters());
            cour.setProfessor(profRepo.findByFullName(course.name()).get());
            courseRepo.save(cour);
        }
        throw new RuntimeException();
    }

    public List<Course> getAll(){
        return courseRepo.findAll();
    }


}