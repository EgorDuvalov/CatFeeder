package com.bsu.catfeeder.controller;

import com.bsu.catfeeder.dto.LogDTO;
import com.bsu.catfeeder.service.LogService;
import com.bsu.catfeeder.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:8080")
@RequestMapping("/cat-feeder")
@RequiredArgsConstructor
public class UserController {
	private final UserService userService;
	private final LogService logService;

	@PostMapping("/login")
	public String login(@RequestParam("username") String username) {
		return userService.login(username);
	}

	@GetMapping("/{userId}/logs")
	public List<LogDTO> getLogs(@PathVariable Long userId) {
		return logService.getUserLogs(userId);
	}
}
