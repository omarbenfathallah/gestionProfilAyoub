package com.example.gestionprofil.Service;


import com.example.gestionprofil.Dto.UserInfosDto;
import com.example.gestionprofil.Entity.User;
import com.example.gestionprofil.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceIm implements  UserService {


    private  UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<User> retrieveAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User addUser(User user) {
        String hashPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashPassword);
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user) {
        String hashPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashPassword);
        return userRepository.save(user);
    }

    @Override
    public void removeUser(Long idUsers) {

        userRepository.deleteById(idUsers);

    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.getUserByMail(email);
    }

    @Override
    public void updateUserInfo(String userEmail, UserInfosDto userInfosDto) {
        User user = userRepository.getUserByMail(userEmail);

        user.setNom(userInfosDto.getNom());
        user.setPrenom(userInfosDto.getPrenom());
        user.setTel(userInfosDto.getTel());
        user.setCin(userInfosDto.getCin());
        userRepository.save(user);

    }


}