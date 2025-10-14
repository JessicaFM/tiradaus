package org.tiradaus.infrastructure.persistence.jpa.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.tiradaus.domain.model.User;
import org.tiradaus.infrastructure.persistence.jpa.entity.UserEntity;

@Mapper(componentModel = "spring")
public interface UserJpaMapper {

    @Mapping(target = "role", ignore = true)
    @Mapping(target = "password" , source = "password")
    UserEntity toEntity(User user);

    @Mapping(target = "role", expression = "java(Role.valueOf(entity.getRole().getName()))")
    User toDomain(UserEntity entity);
}
