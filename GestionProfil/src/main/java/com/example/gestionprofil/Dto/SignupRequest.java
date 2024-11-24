package com.example.gestionprofil.Dto;

import com.example.gestionprofil.Entity.Role;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class SignupRequest {
    private String email;
    private String nom;
    private String prenom;
    private Long tel;
    private Long cin;
    private String password;
    private Role role;


}
