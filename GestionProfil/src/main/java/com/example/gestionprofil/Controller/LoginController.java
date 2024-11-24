package com.example.gestionprofil.Controller;

import com.example.gestionprofil.Dto.LoginRequest;
import com.example.gestionprofil.Dto.LoginResponse;
import com.example.gestionprofil.Entity.User;
import com.example.gestionprofil.Repository.UserRepository;
import com.example.gestionprofil.Service.Jwt.JwtService;
import com.example.gestionprofil.Service.UserService;
import com.example.gestionprofil.Util.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Optional;

@RestController

@RequestMapping("/login")
public class LoginController {

@Autowired
    private final AuthenticationManager authenticationManager;

  private final UserService userService;
    private final JwtUtil jwtUtil;

    private final JwtService jwtService;
    private final UserRepository userRepository;

    public LoginController(AuthenticationManager authenticationManager, UserService userService, JwtUtil jwtUtil, JwtService jwtService, UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.jwtUtil = jwtUtil;
        this.jwtService = jwtService;
        this.userRepository = userRepository;
    }



    @PostMapping
    public LoginResponse login(@RequestBody LoginRequest loginRequest, HttpServletResponse response) throws IOException {
        Optional<User> user = userRepository.findByEmail(loginRequest.getEmail());
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Incorrect email or password.");
        } catch (DisabledException disabledException) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "user is not activated");
            return null;
        }
        final UserDetails userDetails = jwtService.loadUserByUsername(loginRequest.getEmail());
        final String jwt = jwtUtil.generateToken(userDetails.getUsername(), user.get().getRole());

        return new LoginResponse(jwt);
    }
}
