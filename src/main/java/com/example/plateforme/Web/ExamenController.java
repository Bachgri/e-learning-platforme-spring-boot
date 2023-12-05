package com.example.plateforme.Web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.plateforme.Entities.Exam;
import com.example.plateforme.Entities.Question;
import com.example.plateforme.Entities.Response;
import com.example.plateforme.Repository.ExamRepository;
import com.example.plateforme.Repository.QuestionRepository;
import com.example.plateforme.Repository.ResponseRepository;

@RestController
@RequestMapping("")
public class ExamenController {

	@Autowired
	ExamRepository repo;
	@Autowired
	ResponseRepository resRepo;
	@Autowired
	QuestionRepository qRepo;
	
	@PostMapping("/prof/exams")
	public Exam saveExam(@RequestBody Exam e) {
		System.err.println("saving exam");
		return repo.save(e);
	}
	@PutMapping("/prof/exams")
	public Exam updateExam(@RequestBody Exam e) {
		return repo.save(e);
	}
	
	@DeleteMapping("/prof/exams/{id}")
	public Exam deleteExam(@PathVariable("id") long e) {
		Exam ee = repo.findById(e).get();
		repo.deleteById(e);
		return ee;
	}
	
	@PostMapping("/prof/questions")
	public Question saveQuestion(@RequestBody Question e) {
		return qRepo.save(e);
	}
	
	@PutMapping("/prof/questions")
	public Question updateQuestion(@RequestBody Question e) {
		return qRepo.save(e);
	}
	
	@DeleteMapping("/prof/questions/{id}")
	public Question deleteQuestion(@PathVariable("id") long e) {
		Question ee = qRepo.findById(e).get();
		qRepo.deleteById(e);
		return ee;
	}
	
	@PostMapping("/prof/responses") 
	public Response saveResponse(@RequestBody Response e) {
		return resRepo.save(e);
	}
	
	@PutMapping("/prof/responses") 
	public Response updateResponse(@RequestBody Response e) {
		return resRepo.save(e);
	}
	
	@DeleteMapping("/prof/responses/{id}") 
	public Response deleteResponse(@PathVariable("id") long e) {
		Response ee = resRepo.findById(e).get();
		resRepo.deleteById(e);
		return ee;
	}
	
}
