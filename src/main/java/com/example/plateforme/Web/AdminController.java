package com.example.plateforme.Web;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.plateforme.Entities.Professor;
import com.example.plateforme.Entities.Student;
import com.example.plateforme.Repository.StudentRepo;
import com.example.plateforme.Service.ProfService;
import com.example.plateforme.Service.userSer;

@RestController
@RequestMapping("/admin")
public class AdminController {

	ProfService pService;
	StudentRepo sRepo;
	userSer uSer;
	
	
	
	public AdminController(ProfService pService, StudentRepo sRepo, userSer uSer) {
		super();
		this.pService = pService;
		this.sRepo = sRepo;
		this.uSer = uSer;
	}

	

	@GetMapping("/inscriptions")
	public List<Student> inscriptions(){
		return uSer.inscription();
	}
	@GetMapping("/profs")
	public List<Professor> profs(){
		return pService.get();
	}
	
	@PostMapping("/profs")
	public Professor profs(@RequestBody Professor p){
		return pService.post(p);
	}

	@PostMapping("/validate")
	public Student validate(@RequestBody Student s) throws Exception {
		s.setValider(true);

			uSer.sendMailToStudent(s.getEmail());


		return sRepo.save(s);
	}
	
	
	 

}
