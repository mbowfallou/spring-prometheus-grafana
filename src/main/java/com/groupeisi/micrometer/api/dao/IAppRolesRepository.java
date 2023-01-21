package com.groupeisi.micrometer.api.dao;

import com.groupeisi.micrometer.api.entities.AppRolesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAppRolesRepository extends JpaRepository<AppRolesEntity, Integer> {
    AppRolesEntity findByNom(String nom);
}
