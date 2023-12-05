package com.example.plateforme.serviceInt;

import java.util.List;

import com.example.plateforme.Entities.NavItems;

public interface NavItemsServiceInt {
	public List<NavItems> loadByRole(String role);
}
