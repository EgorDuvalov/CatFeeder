package com.bsu.catfeeder.service;

import com.bsu.catfeeder.dto.LogDto;
import com.bsu.catfeeder.entity.Log;
import com.bsu.catfeeder.mapper.LogMapper;
import com.bsu.catfeeder.repository.LogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LogService {
    private final LogRepository repository;
    private final LogMapper mapper;

    public void addLog(Log log) {
        repository.save(log);
    }

    public List<LogDto> getAllLogs() {
        List<Log> logsFromDatabase = repository.findAll();
        return mapper.mapToLogDtoList(logsFromDatabase);
    }
}
