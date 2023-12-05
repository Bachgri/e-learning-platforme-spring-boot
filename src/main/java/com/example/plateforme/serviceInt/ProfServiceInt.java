package com.example.plateforme.serviceInt;

import java.util.List;

import com.example.plateforme.Entities.Professor;

public interface ProfServiceInt {
	public List<Professor> get();
	public Professor get(long id);
	public Professor post(Professor p);
	public Professor put(Professor p);
	public Professor delete(long id);
	
}
