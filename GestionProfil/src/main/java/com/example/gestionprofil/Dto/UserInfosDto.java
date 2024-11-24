package com.example.gestionprofil.Dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserInfosDto {
    private  String nom;
    private  String prenom;
    private  Long tel;
    private Long cin;
}
