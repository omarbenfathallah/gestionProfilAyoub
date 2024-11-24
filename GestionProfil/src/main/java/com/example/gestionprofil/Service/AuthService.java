package com.example.gestionprofil.Service;


import com.example.gestionprofil.Dto.SignupRequest;
import com.example.gestionprofil.Entity.User;

public interface AuthService {
    User createUser(SignupRequest signupRequest);
}
