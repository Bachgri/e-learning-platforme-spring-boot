package com.example.plateforme.Service;

import com.example.plateforme.Entities.*;
import com.example.plateforme.Repository.CourseRepo;
import com.example.plateforme.Repository.ExamRepository;
import com.example.plateforme.Repository.NotesRepo;
import com.example.plateforme.Repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EtudiantService {

    @Autowired
    StudentRepo studentRepo;

    @Autowired CourseService courseService;


    @Autowired
    CourseRepo courseRepo;

    @Autowired
    NotesRepo notesRepo;





    public Student affectCourse(Long id, Long id1){
        Course c = courseService.get(id1);
        if(studentRepo.findById(id).isPresent()){
            Student s = studentRepo.findById(id).get();
            List<Course> list = s.getCourses();
            if(!list.contains(c)){
                list.add(c);
            }

            s.setCourses(list);
            return studentRepo.save(s);
        }
        throw new RuntimeException();
    }

    public List<Student> getAll(){
        return studentRepo.findAll();
    }

    public void validation(Long id){
        if(studentRepo.findById(id).isPresent()){
            Student s = studentRepo.findById(id).get();
            s.setValider(true);
            studentRepo.save(s);
        }
        throw new RuntimeException();
    }

    public List<Course> studentCourses(Long id){
        if(studentRepo.findById(id).isPresent()){
            Student s = studentRepo.findById(id).get();
            return s.getCourses();
        }
        throw new RuntimeException();
    }


    public Notes addNote(Long idS, Long idC, double note){
        if(studentRepo.findById(idS).isPresent() && courseRepo.findById(idC).isPresent()){
            Notes n = new Notes();
            n.setNote(note);
            n.setStudent(studentRepo.findById(idS).get());
            n.setCourse(courseRepo.findById(idC).get());
            return notesRepo.save(n);
        }
        throw new RuntimeException("error");
    }
}