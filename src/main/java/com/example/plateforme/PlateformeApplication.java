package com.example.plateforme;

import com.example.plateforme.DTO.userDTO;
import com.example.plateforme.Entities.Professor;
import com.example.plateforme.Entities.Role;
import com.example.plateforme.Repository.ProfRepo;
import com.example.plateforme.Service.roleSer;
import com.example.plateforme.Service.userSer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;


@SpringBootApplication
public class PlateformeApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlateformeApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(roleSer roleSer, userSer userS, ProfRepo prp){
		return result -> {
//			roleSer.create("Student");
//			roleSer.create("Professor");
//			roleSer.create("Admin");
			Professor p = new Professor();
			p.setFullName("oualid lachgar");
			p.setEmail("oualidlachgar7@gmail.com");
			p.setPassword("oualidlachgar7@gmail.com");
			p.setRole(roleSer.roleByName("Professor"));
			System.err.println(prp.findAll().get(0).getFullName()); 
			//p.setSpecialite("INFO");
//			System.err.println("insert prof");
//			prp.save(p);
//			roleSer.create("Student");
//			roleSer.create("Professor");
//			roleSer.create("Admin");
//			userDTO user = new userDTO(null,"admin01@gmail.com","admin01@gmail.com","admin01@gmail.com","admin01@gmail.com","0608539556","Professor"
//					,new Date(),"lwad");
//			userS.createP(user);
		};
	}

}
