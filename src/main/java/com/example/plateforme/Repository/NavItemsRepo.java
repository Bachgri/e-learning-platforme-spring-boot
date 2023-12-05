package com.example.plateforme.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.plateforme.Entities.NavItems;

public interface NavItemsRepo extends JpaRepository<NavItems, Long> {

	public List<NavItems> findByRole(String role);
	
}
