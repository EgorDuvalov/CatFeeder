package com.bsu.catfeeder.service;

import com.bsu.catfeeder.dto.CreateScheduleDTO;
import com.bsu.catfeeder.dto.ScheduleDTO;
import com.bsu.catfeeder.entity.Schedule;
import com.bsu.catfeeder.entity.User;
import com.bsu.catfeeder.logger.Logger;
import com.bsu.catfeeder.mapper.ScheduleMapper;
import com.bsu.catfeeder.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import lombok.val;
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
		User user = userService.retrieveUser(userId);
		Schedule schedule = scheduleMapper.mapToEntity(dto);
		schedule.setUser(user);
		schedule = scheduleRepository.save(schedule);

		val result = scheduleMapper.mapToDto(schedule);
		logger.info(user, "User added new schedule");
		return result;
	}

	public void deleteSchedule(Long userId, Long scheduleId) {
		User user = userService.retrieveUser(userId);
		retrieveSchedule(scheduleId);

		scheduleRepository.deleteById(scheduleId);
		logger.info(user, "User deleted schedule " + scheduleId);
	}

	public Schedule retrieveSchedule(Long id) {
		Optional<Schedule> schedule = scheduleRepository.findById(id);
		if (schedule.isEmpty()) {
			ResponseStatusException e = new ResponseStatusException(
				HttpStatus.NOT_FOUND,
				format("Schedule with id %d is not found", id));

			logger.error(null,
				format("Schedule %d is not found", id),
				e.getReason());
			throw e;
		}

		return schedule.get();
	}
}
