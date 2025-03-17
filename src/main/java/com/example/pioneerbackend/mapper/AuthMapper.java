package com.example.pioneerbackend.mapper;

import com.example.pioneerbackend.dto.auth.AuthorisationRequest;
import com.example.pioneerbackend.entity.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AuthMapper {

    User toEntity(AuthorisationRequest request);
}
