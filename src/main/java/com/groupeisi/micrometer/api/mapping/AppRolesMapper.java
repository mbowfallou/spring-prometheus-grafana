package com.groupeisi.micrometer.api.mapping;

import com.groupeisi.micrometer.api.dto.AppRoles;
import com.groupeisi.micrometer.api.entities.AppRolesEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AppRolesMapper {
    AppRoles toAppRoles(AppRolesEntity appRolesEntity);
    AppRolesEntity fromAppRoles(AppRoles appRoles);
}
