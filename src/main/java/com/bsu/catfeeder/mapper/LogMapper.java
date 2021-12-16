package com.bsu.catfeeder.mapper;

import com.bsu.catfeeder.dto.LogDto;
import com.bsu.catfeeder.entity.Log;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LogMapper {
    List<LogDto> mapToLogDtoList(List<Log> logList);
}
