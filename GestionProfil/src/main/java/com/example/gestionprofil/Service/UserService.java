package com.example.gestionprofil.Service;


import com.example.gestionprofil.Dto.UserInfosDto;
import com.example.gestionprofil.Entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    public List<User> retrieveAllUser();
    public User addUser(User user);
    public User updateUser(User user);
    void removeUser(Long idUsers);

    User getUserById(Long id);

    Optional<User> getUserByEmail(String email);

    User findUserByEmail(String email);
    void updateUserInfo(String userEmail, UserInfosDto userInfosDto);

}
