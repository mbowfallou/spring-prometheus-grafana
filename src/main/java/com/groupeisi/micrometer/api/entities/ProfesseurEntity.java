package com.groupeisi.micrometer.api.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = "professeurs")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfesseurEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 50, nullable = false)
    private String nom;
    @Column(length = 80, nullable = false)
    private String prenom;
    @Column(length = 200)
    private String adresse;
    @Column(length = 150, unique = true)
    @NotBlank(message = "Un email est obligatoire !")
    private String email;
    @NotBlank(message = "Un mot de passe est obligatoire !")
    private String password;
    private int etat;
    @ManyToMany
    private List<AppRolesEntity> appRolesEntities;
}
