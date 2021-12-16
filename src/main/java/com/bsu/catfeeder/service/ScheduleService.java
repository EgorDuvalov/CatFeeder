package com.bsu.catfeeder.service;

import com.bsu.catfeeder.dto.CreateScheduleDTO;
import com.bsu.catfeeder.dto.ScheduleDTO;
import com.bsu.catfeeder.entity.Schedule;
import com.bsu.catfeeder.entity.User;
import com.bsu.catfeeder.mapper.ScheduleMapper;
import com.bsu.catfeeder.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static java.lang.String.format;

@Service
@Transactional
@RequiredArgsConstructor
public class ScheduleService {
	private final ScheduleRepository scheduleRepository;
	private final ScheduleMapper scheduleMapper;
	private final UserService userService;
	private final Logger logger;

	public List<ScheduleDTO> getSchedules(Long userId) {
		User owner = userService.retrieveUser(userId);
		List<Schedule> schedules = owner.getSchedules();

		return scheduleMapper.mapToDtoList(schedules);
	}

	public ScheduleDTO addSchedule(Long userId, CreateScheduleDTO dto) {
		Schedule schedule = scheduleMapper.mapToEntity(dto);
		schedule.setUser(userService.retrieveUser(userId));
		schedule = scheduleRepository.save(schedule);
		logger.info(format("User %d added new schedule", userId));

		return scheduleMapper.mapToDto(schedule);
	}

	public void deleteSchedule(Long userId, Long scheduleId) {
		userService.retrieveUser(userId);
		retrieveSchedule(scheduleId);
		logger.info("User "+ userId + "deleted schedule "+ scheduleId);

		scheduleRepository.deleteById(scheduleId);
	}

	public Schedule retrieveSchedule(Long id) {
		Optional<Schedule> schedule = scheduleRepository.findById(id);
		if (schedule.isEmpty()) {
			throw new ResponseStatusException(
				HttpStatus.NOT_FOUND,
				format("Schedule with id %d is not found", id));
		}

		return schedule.get();
	}
}
