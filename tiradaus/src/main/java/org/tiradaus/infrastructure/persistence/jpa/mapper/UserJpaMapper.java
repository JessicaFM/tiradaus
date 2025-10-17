package org.tiradaus.infrastructure.persistence.jpa.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.tiradaus.domain.model.User;
import org.tiradaus.infrastructure.persistence.jpa.entity.UserEntity;

@Mapper(componentModel = "spring")
public interface UserJpaMapper {

    @Mapping(target = "role",
            expression = "java(new org.tiradaus.infrastructure.persistence.jpa.entity.RoleEntity(user.roleId()))")    @Mapping(target = "isActive", source = "active")
    @Mapping(target = "userName" , source = "userName")
    UserEntity toEntity(User user);

    @Mapping(target = "roleId", source = "role.id")
    @Mapping(target = "active", source = "isActive")
    @Mapping(target = "userName", source = "userName")
    User toDomain(UserEntity entity);
}
