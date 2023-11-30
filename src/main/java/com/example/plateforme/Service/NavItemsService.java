package com.example.plateforme.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.plateforme.Entities.NavItems;
import com.example.plateforme.Repository.NavItemsRepo;
import com.example.plateforme.serviceInt.NavItemsServiceInt;

@Service
public class NavItemsService implements NavItemsServiceInt {

	private NavItemsRepo repo;
	
	
	public NavItemsService(NavItemsRepo repo) {
		super();
		this.repo = repo;
	}


	@Override
	public List<NavItems> loadByRole(String role) { 
		return repo.findByRole(role);
	}
	 

}
