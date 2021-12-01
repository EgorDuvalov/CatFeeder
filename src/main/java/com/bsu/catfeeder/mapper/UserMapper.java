package com.bsu.catfeeder.mapper;

import com.bsu.catfeeder.dto.CreateUserDTO;
import com.bsu.catfeeder.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

	User mapToEntity(CreateUserDTO dto);
}
