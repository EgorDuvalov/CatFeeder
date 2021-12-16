package com.bsu.catfeeder.mapper;

import com.bsu.catfeeder.dto.LogDto;
import com.bsu.catfeeder.entity.Log;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LogMapper {

    @Mapping(source = "creationTime", target = "creationTime")
    @Mapping(source = "message", target = "message")
    List<LogDto> mapToLogDtoList(List<Log> logList);
}
