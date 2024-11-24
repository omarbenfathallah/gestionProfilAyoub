package com.example.gestionprofil.Service.Jwt;

import com.example.gestionprofil.Entity.User;
import com.example.gestionprofil.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@AllArgsConstructor
public class JwtService implements UserDetailsService {
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user =  userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found With  Email: "+email));
        return  new org.springframework.security.core.userdetails
                .User(user.getEmail(),user.getPassword(), Collections.emptyList()) ;
    }




}
