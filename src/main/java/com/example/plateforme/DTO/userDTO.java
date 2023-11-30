package com.example.plateforme.DTO;

import java.util.Date;

public record userDTO(Long id,
                      String Name,
                      String Password,
                      String Ville,
                      String Email,
                      String Tel,
                      String Role,
                      Date DateN,
                      String Address) {}
