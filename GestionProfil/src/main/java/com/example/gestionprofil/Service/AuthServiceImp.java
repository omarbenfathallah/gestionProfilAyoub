package com.example.gestionprofil.Service;


import com.example.gestionprofil.Dto.SignupRequest;
import com.example.gestionprofil.Entity.User;
import com.example.gestionprofil.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthServiceImp implements AuthService{
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    @Override
    public User createUser(SignupRequest signupRequest) {
        if (userRepository.existsByEmail(signupRequest.getEmail())) {
            return null;
        }

        User user = new User();
        BeanUtils.copyProperties(signupRequest,user);


        String hashPassword = passwordEncoder.encode(signupRequest.getPassword());
        user.setPassword(hashPassword);
        User student = userRepository.save(user);
        user.setId(student.getId());
        return user;
    }
}
