package com.example.gestionprofil.Controller;

import com.example.gestionprofil.Dto.UserInfosDto;
import com.example.gestionprofil.Entity.User;
import com.example.gestionprofil.Service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {

    private UserService userService;


    @PostMapping("/add")
    public User addEtudiant(@RequestBody User user) {
        return userService.addUser(user);
    }

    @GetMapping("/getallUser")
    public List<User> retrieveAllUser() {
        return userService.retrieveAllUser();
    }

    @PutMapping("/update")
    public User updateUser(@RequestBody User user) {
        return userService.updateUser(user);

    }

    @DeleteMapping("/delete/{idUsers}")
    void removeUser(@PathVariable("idUsers") Long idUsers) {
        userService.removeUser(idUsers);
    }

    @GetMapping("/getbyid/{id}")
    public User getUserById(@PathVariable("id") Long id) {
        return userService.getUserById(id);
    }

    @GetMapping("/getbyemail/{email}")
    public Optional<User> getUserById(@PathVariable("email") String email) {
        return userService.getUserByEmail(email);
    }


    @PutMapping("/updateUser")
    public ResponseEntity<Map<String, String>> updateUserInfo(Authentication authentication, @RequestBody UserInfosDto userInfosDto) {
        String userEmail = authentication.getName();
        userService.updateUserInfo(userEmail, userInfosDto);

        Map<String, String> response = new HashMap<>();
        response.put("message", "User information updated successfully");

        return ResponseEntity.ok(response);
    }


    @GetMapping("/current")
    public ResponseEntity<User> getCurrentUser(Authentication authentication) {
        // Vérifier si l'utilisateur est authentifié
        if (authentication != null && authentication.isAuthenticated()) {
            // Récupérer le nom d'utilisateur de l'utilisateur actuel
            String email = authentication.getName();
            // Rechercher l'utilisateur dans la base de données en utilisant son nom d'utilisateur
            User user = userService.findUserByEmail(email);
            if (user != null) {
                // Retourner l'utilisateur trouvé avec le statut HTTP OK (200)
                return ResponseEntity.ok(user);
            }
        }
        // Si l'utilisateur n'est pas trouvé ou n'est pas authentifié, retourner un statut HTTP NOT FOUND (404)
        return ResponseEntity.notFound().build();
    }

}
