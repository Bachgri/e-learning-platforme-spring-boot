package com.example.plateforme.Service;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.plateforme.Entities.Professor;
import com.example.plateforme.Repository.ProfRepo;
import com.example.plateforme.serviceInt.ProfServiceInt;

@Service
public class ProfService implements ProfServiceInt {

	private ProfRepo repo;
	 PasswordEncoder passwordEncoder;
	
	
	public ProfService(ProfRepo repo,PasswordEncoder passwordEncoder) {
		super();
		this.repo = repo;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public List<Professor> get() { 
		return repo.findAll();
	}
	
	@Override
	public Professor get(long id) { 
		return repo.findById(id).get();
	}

	@Override
	public Professor post(Professor p) { 
		p.setPassword(passwordEncoder.encode(p.getPassword()));
		return repo.save(p);
	}

	@Override
	public Professor put(Professor p) {
	 
		return repo.save(p);
	}

	@Override
	public Professor delete(long id) { 
		Professor p = get(id);
		repo.deleteById(id);
		return p;
	}

 

}
