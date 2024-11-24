package com.example.gestionprofil.Entity;


import jakarta.persistence.*;
import lombok.*;




@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String prenom;
    private String password;
    private Long tel;
    private Long cin;
    private String email;
    @Enumerated(EnumType.STRING)
    private Role role;

}
