package com.example.plateforme.Web;

import com.example.plateforme.DTO.CourseDTO;
import com.example.plateforme.Entities.Chapter;
import com.example.plateforme.Entities.Course;
import com.example.plateforme.Entities.Professor;
import com.example.plateforme.Entities.Student;
import com.example.plateforme.Repository.ChapterRepo;
import com.example.plateforme.Repository.ProfRepo;
import com.example.plateforme.Service.CourseService;
import com.example.plateforme.Service.EtudiantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/Student")
public class SecondController {

    @Autowired
    EtudiantService etudiantService;

    @Autowired
    CourseService courseService;

    @Autowired
    ProfRepo profRepo;
    
    @Autowired
    ChapterRepo chapterRepo;
    
    
    
    @GetMapping("/chapters")
    public List<Chapter> chapters(){
        return chapterRepo.findAll();
    }
    @PostMapping("/chapters")
    public Chapter saveChapter(@RequestBody Chapter ch){
        return chapterRepo.save(ch);
    }
    @GetMapping("/chapters/{id}")
    public List<Chapter> getChapter(){
        return chapterRepo.findAll();
    }
    @PutMapping("/chapters")
    public Chapter putChapter(@RequestBody Chapter ch){
        return chapterRepo.save(ch);
    }
    
    @PutMapping("/affectCourse/{student}/{id}")
    public Student affect(@PathVariable Long student, @PathVariable Long id){
        return etudiantService.affectCourse(student,id);
    }


    @GetMapping("/course/{id}")
    public Course get(@PathVariable Long  id){
        return courseService.get(id);
    }

    @PostMapping("/courseCreation")
    public Course CourseCreation(@RequestBody Course  course){
        return courseService.create(course);
    }

    @PutMapping("/updateCourse")
    public void updateCourse(@RequestBody Course course){
        courseService.create(course);
    }

    @DeleteMapping("/deleteCourse/{id}")
    public void deleteCourse(@PathVariable Long id){
        courseService.delete(id);
    }


    @GetMapping("/allCourses")
    public List<Course> courses(){
        return courseService.getAll();
    }

    @GetMapping("/allStudents")
    public List<Student> students(){
        return etudiantService.getAll();
    }

    @GetMapping("/allProfessors")
    public List<Professor> professors(){
        return profRepo.findAll();
    }


    @PutMapping("/validate/{id}")
    public void validate(@PathVariable Long id){
        etudiantService.validation(id);
    }

    @GetMapping("/studentCourses/{id}")
    public List<Course> studentCourses(@PathVariable Long id){
        return etudiantService.studentCourses(id);
    }
}
