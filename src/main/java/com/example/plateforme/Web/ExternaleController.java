package com.example.plateforme.Web;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.plateforme.Entities.NavItems;
import com.example.plateforme.Repository.NavItemsRepo;
import com.example.plateforme.Service.NavItemsService;

@RestController
@RequestMapping("/api")
public class ExternaleController {
	private NavItemsRepo service;
	
	public ExternaleController(NavItemsRepo service) {
		super();
		this.service = service;
	}

	 

	@GetMapping("items/{role}")
	public List<NavItems> get(@PathVariable("role") String role){
		return service.findByRole(role);
	}
		
}						