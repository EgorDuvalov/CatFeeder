package com.bsu.catfeeder.mapper;

import com.bsu.catfeeder.dto.CreateScheduleDTO;
import com.bsu.catfeeder.entity.Schedule;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ScheduleMapper {
	Schedule mapToEntity(CreateScheduleDTO dto);
}
