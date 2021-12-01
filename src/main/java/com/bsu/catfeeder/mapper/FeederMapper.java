package com.bsu.catfeeder.mapper;

import com.bsu.catfeeder.dto.CreateFeederDto;
import com.bsu.catfeeder.entity.Feeder;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FeederMapper {
	Feeder mapToEntity(CreateFeederDto dto);
}
