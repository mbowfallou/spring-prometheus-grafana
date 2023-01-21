package com.groupeisi.micrometer.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Professeur {
    private Integer id;
    //@NotNull(message = "Le nom ne doit pas etre nul!")
    private String nom;
    //@NotNull(message = "Le prenom ne doit pas etre nul!")
    private String prenom;
    private String adresse;
    @NotBlank(message = "L'Email ne doit pas etre nul!")
    @Email(message = "L'adresse fournie n'est pas au bon format")
    private String email;
    @NotNull(message = "Le mot de passe ne peut pas être nul!")
    @NotBlank(message = "Le mot de passe ne peut pas être nul!")
    private String password;
    private int etat;
}
