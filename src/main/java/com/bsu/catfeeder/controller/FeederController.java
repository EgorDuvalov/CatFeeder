package com.bsu.catfeeder.controller;

import com.bsu.catfeeder.dto.CreateFeederDto;
import com.bsu.catfeeder.dto.FeederDTO;
import com.bsu.catfeeder.service.FeederService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:8080")
@RequestMapping("/catFeeder/{userId}/feeders")
@RequiredArgsConstructor
public class FeederController {
	private final FeederService feederService;

	@GetMapping
	public List<FeederDTO> getFeeders(@PathVariable Long userId) {
		return feederService.getFeedersOfUser(userId);
	}

	@PostMapping("/add")
	public FeederDTO addFeeder(@PathVariable Long userId, @Valid @RequestBody CreateFeederDto dto) {
		return feederService.addFeeder(userId, dto);
	}

	@DeleteMapping("/{feederId}")
	public void deleteFeeder(@PathVariable Long userId, @PathVariable Long feederId) {
		feederService.deleteFeeder(userId, feederId);
	}

	@PutMapping("/{feederId}/add-schedule")
	private void setSchedule(@PathVariable Long userId, @PathVariable Long feederId,
		@RequestParam(name = "scheduleId") Long scheduleId) {
		feederService.setSchedule(userId, feederId, scheduleId);
	}

	@PutMapping("/{feederId}/activate")
	private void activateFeeder(@PathVariable Long userId, @PathVariable Long feederId) {
		feederService.activateFeeder(userId, feederId);
	}
}
