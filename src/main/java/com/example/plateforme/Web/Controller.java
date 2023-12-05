package com.example.plateforme.Web;

import com.example.plateforme.DTO.CourseDTO;
import com.example.plateforme.DTO.userDTO;
import com.example.plateforme.Entities.App_User;
import com.example.plateforme.Entities.Course;
import com.example.plateforme.Entities.Student;
import com.example.plateforme.Service.CourseService;
import com.example.plateforme.Service.EtudiantService;
import com.example.plateforme.Service.userSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/Authentification")
public class Controller {

    @Autowired
    userSer userService;

    @Autowired
    EtudiantService etudiantService;

    @Autowired
    CourseService courseService;

    @PostMapping("/Creation")
    public App_User creation(@RequestBody userDTO user){
        return userService.create(user);
    }

    @PostMapping("/Login")
    public Map<String, String> login(@RequestBody Map<String, String> request){
        return userService.login(request.get("email"),request.get("password"));
    }
    public void show(){
        System.out.print(5);
    }

    @PostMapping("/prof")
    public App_User creationP(@RequestBody userDTO user){
        return userService.createP(user);
    }

    @PostMapping("/CreationGoogle")
    public App_User creationG(@RequestBody userDTO user){
        return userService.createGoogle(user);
    }



    //Course endpoints
    @PutMapping("/affectCourse/{student}/{id}")
    public Student affect(@PathVariable Long student, @PathVariable Long id){
        return etudiantService.affectCourse(student,id);
    }


    @GetMapping("/course/{id}")
    public Course get(@PathVariable Long  id){
        return courseService.get(id);
    }

    @PostMapping("/courseCreation")
    public Course CourseCreation(@RequestBody CourseDTO course){
        return courseService.post(course);
    }

    @PutMapping("/updateCourse")
    public void updateCourse(@RequestBody CourseDTO course){
        courseService.put(course);
    }

    @DeleteMapping("/deleteCourse/{id}")
    public void deleteCourse(@PathVariable Long id){
        courseService.delete(id);
    }
}