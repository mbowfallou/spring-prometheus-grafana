package com.groupeisi.micrometer.api.mapping;

import com.groupeisi.micrometer.api.dto.Professeur;
import com.groupeisi.micrometer.api.entities.ProfesseurEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProfesseurMapper {
    Professeur toProfesseur(ProfesseurEntity professeurEntity);
    ProfesseurEntity fromProfesseur(Professeur professeur);
}
