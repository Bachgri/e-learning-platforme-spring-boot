package com.example.plateforme.Service;

import com.example.plateforme.DTO.Details;
import com.example.plateforme.DTO.userDTO;
import com.example.plateforme.Entities.App_User;
import com.example.plateforme.Entities.Professor;
import com.example.plateforme.Entities.Role;
import com.example.plateforme.Entities.Student;
import com.example.plateforme.Repository.ProfRepo;
import com.example.plateforme.Repository.RepoRole;
import com.example.plateforme.Repository.RepoUser;
import com.example.plateforme.Repository.StudentRepo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class userSer {
	String role;
    @Autowired
    RepoUser repoUser;

    @Autowired
    roleSer repoSer;

    @Autowired
    JavaMailSender sender;

    @Autowired
    StudentRepo studentRepo;

    @Autowired
    ProfRepo profRepo;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtEncoder jwtEncoder;

    public Student create(userDTO user){
        Role role = repoSer.roleByName(user.Role());

        if(repoUser.findByEmail(user.Email()).isPresent()){
            throw new RuntimeException(String.format("Email %s used",user.Email()));
        }
        Student x =new Student();
        x.setRole(role);
        x.setVille(user.Ville());
        x.setTel(user.Tel());
        x.setEmail(user.Email());
        x.setPassword(passwordEncoder.encode(user.Password()));
        x.setFullName(user.Name());
        x.setDateN(user.DateN());
        return studentRepo.save(x);
    }

    public Professor createP(userDTO user){
        Role role = repoSer.roleByName(user.Role());
        if(repoUser.findByEmail(user.Email()).isPresent()){
            throw new RuntimeException(String.format("Email %s used",user.Email()));
        }
        Professor x =new Professor();
        x.setRole(role);
        x.setEmail(user.Email());
        x.setPassword(passwordEncoder.encode(user.Password()));
        x.setFullName(user.Name());
        //x.setSpecialite(user.Address());
        return profRepo.save(x);
    }

    public Map<String, String> login(String email, String password){
        try {

            UsernamePasswordAuthenticationToken user = new UsernamePasswordAuthenticationToken(
                    email,password);
            
            Authentication auth = authenticationManager.authenticate(user);
           
            auth.getAuthorities().forEach(r->{
            	role=r.getAuthority().toString();
            });
            String scope = auth.getAuthorities().stream().map(p -> p.getAuthority()).collect(Collectors.joining(" "));
            Instant instant = Instant.now();
            
            if(repoUser.findByEmail(email).get().getRole().getName().equals("Student")) {
            	Student s = (Student) repoUser.findByEmail(email).get();
            	  JwtClaimsSet jwt = JwtClaimsSet.builder()
                          .issuedAt(instant)
                          .expiresAt(instant.plus(60, ChronoUnit.MINUTES))
                          .subject(email)
                          .claim("scope", scope)
                          .claim("valid", s.isValider())
                          .claim("id", s.getId())
                          .build();
            	  JwtEncoderParameters jw = JwtEncoderParameters.from(JwsHeader.with(MacAlgorithm.HS512).build(), jwt);
                  String token = jwtEncoder.encode(jw).getTokenValue();
                  System.out.print(token);
                  Map<String, String> map = new HashMap<>();
                  map.put("acces_token", token);
                  map.put("role", role);
                 
                  return map;
            }else {
            	JwtClaimsSet jwt = JwtClaimsSet.builder()
                        .issuedAt(instant)
                        .expiresAt(instant.plus(10, ChronoUnit.MINUTES))
                        .subject(email)
                        .claim("scope", scope)
                        
                        .build();
            	 JwtEncoderParameters jw = JwtEncoderParameters.from(JwsHeader.with(MacAlgorithm.HS512).build(), jwt);
                 String token = jwtEncoder.encode(jw).getTokenValue();
                 System.out.print(token);
                 Map<String, String> map = new HashMap<>();
                 map.put("acces_token", token);
                 map.put("role", role);
                 if(role.compareTo("Professor")==0) {
                	 System.err.println("Prof name  : "+user.getName());
                	 long id =  profRepo.findByEmail(user.getName()).get().getId();
                	System.err.println("role "+role + "\nid: "+id); 
               	  	map.put("id",id+"" );
                 }
                 return map;
            }
            
            
            //sf tester db fel front
           
        }catch(Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());

        }

    }

    public Student createGoogle(userDTO user){
        Role role = repoSer.roleByName("Student");
        if(repoUser.findByEmail(user.Email()).isPresent()){
            throw new RuntimeException(String.format("Email %s used",user.Email()));
        }
        Student x =new Student();
        x.setRole(role);
        x.setEmail(user.Email());
        x.setPassword(passwordEncoder.encode(user.Password()));
        x.setValider(false);
        return studentRepo.save(x);
    }
    public List<Student> inscription(){
    	return studentRepo.findByValider(false);
    }



    public void sendMailToStudent(String email) throws Exception {

        if(!repoUser.findByEmail(email).isPresent()){
            throw new RuntimeException() ;
        }
        Random random = new Random();
        Integer number = (random.nextInt(900000)+100000);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("anass.cherkawi.01@gmail.com");
        message.setSubject("Account Created Successfully");
        message.setText("Welcome into our platform");
        message.setTo(email);

        sender.send(message);

    }
}