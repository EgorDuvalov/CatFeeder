package com.bsu.catfeeder.mapper;

import com.bsu.catfeeder.dto.LogDTO;
import com.bsu.catfeeder.entity.Log;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LogMapper {
	@Mapping(target = "username", source = "user.username")
	List<LogDTO> mapToDtoList(List<Log> entities);
}
