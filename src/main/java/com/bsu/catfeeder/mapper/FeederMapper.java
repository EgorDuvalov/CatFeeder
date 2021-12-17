package com.bsu.catfeeder.mapper;

import com.bsu.catfeeder.dto.CreateFeederDto;
import com.bsu.catfeeder.dto.FeederDTO;
import com.bsu.catfeeder.entity.Feeder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FeederMapper {
	Feeder mapToEntity(CreateFeederDto dto);

	@Mapping(target = "owner", source = "user.username")
	@Mapping(target = "schedule", source = "schedule.name")
	FeederDTO mapToDto(Feeder entity);

	@Mapping(target = "owner", source = "user.username")
	@Mapping(target = "schedule", source = "schedule.name")
	List<FeederDTO> mapToDtoList(List<Feeder> entities);

	@Mapping(target = "id", ignore = true)
	@Mapping(target = "status", ignore = true)
	@Mapping(target = "schedule", ignore = true)
	void updateFromDto(FeederDTO dto, @MappingTarget Feeder feeder);
}
