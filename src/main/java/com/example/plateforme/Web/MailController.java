package com.example.plateforme.Web;

import com.example.plateforme.DTO.Details;
import com.example.plateforme.Entities.App_User;
import com.example.plateforme.Entities.Student;
import com.example.plateforme.Repository.RepoUser;
import com.example.plateforme.Repository.StudentRepo;
import com.nimbusds.jose.shaded.gson.Gson;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@Controller
@RequestMapping("/Authentification")
public class MailController {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private JavaMailSender sender;

    @Autowired
    StudentRepo studentRepo;

    @Autowired
    private RepoUser repoUser;
    @RequestMapping("/Check")
    public ResponseEntity<Integer> sendMail(@RequestBody Details details) throws Exception {
        System.out.print(details);
        if(!repoUser.findByEmail(details.email()).isPresent()){
            throw new RuntimeException() ;
        }
        Random random = new Random();
        Integer number = (random.nextInt(900000)+100000);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("anass.cherkawi.01@gmail.com");
        message.setSubject("Change Password");
        message.setText("Your code is :"+ number);
        message.setTo(details.email());

        sender.send(message);
        return ResponseEntity.ok(number);
    }


    @PutMapping("/Change")
    public ResponseEntity<String> changePass(@RequestBody Details details){
        Student s = (Student)repoUser.findByEmail(details.email()).get();
        System.err.println(s.getPassword());
        s.setPassword(passwordEncoder.encode(details.password()));
        System.err.println(s);
        System.err.println(details.password());

        studentRepo.save(s);
        System.err.println(s.getPassword());

        return ResponseEntity.ok(new Gson().toJson("'status':'Successfully changed'"));
    }




}
