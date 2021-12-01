package com.bsu.catfeeder.controller;

import com.bsu.catfeeder.dto.CreateScheduleDTO;
import com.bsu.catfeeder.entity.Schedule;
import com.bsu.catfeeder.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:8080")
@RequestMapping("/catFeeder/{userId}/schedules")
@RequiredArgsConstructor
public class ScheduleController {
	private final ScheduleService scheduleService;

	@GetMapping
	public List<Schedule> getSchedules(@PathVariable Long userId) {
		return scheduleService.getSchedules(userId);
	}

	@PostMapping("/add")
	public void addSchedule(@PathVariable Long userId, @Valid @RequestBody CreateScheduleDTO dto) {
		scheduleService.addSchedule(userId, dto);
	}

	@DeleteMapping("/{scheduleId}")
	public void deleteSchedule(@PathVariable Long userId, @PathVariable Long scheduleId) {
		scheduleService.deleteSchedule(userId, scheduleId);
	}
}
