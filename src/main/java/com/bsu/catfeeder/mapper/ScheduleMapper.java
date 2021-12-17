package com.bsu.catfeeder.mapper;

import com.bsu.catfeeder.dto.CreateScheduleDTO;
import com.bsu.catfeeder.dto.ScheduleDTO;
import com.bsu.catfeeder.entity.Schedule;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ScheduleMapper {
	Schedule mapToEntity(CreateScheduleDTO dto);

	ScheduleDTO mapToDto(Schedule entity);

	List<ScheduleDTO> mapToDtoList(List<Schedule> entities);

	@Mapping(target = "id", ignore = true)
	void updateFromDto(ScheduleDTO dto, @MappingTarget Schedule entity);
}
