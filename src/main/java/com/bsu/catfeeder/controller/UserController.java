package com.bsu.catfeeder.controller;

import com.bsu.catfeeder.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("http://localhost:8080")
@RequestMapping("/catFeeder")
@RequiredArgsConstructor
public class UserController {
	private final UserService userService;

	@PostMapping("/login")
	public void login(@RequestParam("username") String username) {
		userService.login(username);
	}
}
