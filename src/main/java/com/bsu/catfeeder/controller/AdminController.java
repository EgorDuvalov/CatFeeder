package com.bsu.catfeeder.controller;

import com.bsu.catfeeder.dto.CreateUserDTO;
import com.bsu.catfeeder.dto.ModeratingFeederDto;
import com.bsu.catfeeder.entity.User;
import com.bsu.catfeeder.service.FeederService;
import com.bsu.catfeeder.service.UserService;
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
@RequestMapping("/cat-feeder/admin")
@RequiredArgsConstructor
public class AdminController {
	private final FeederService feederService;
	private final UserService userService;

	@GetMapping("/moderating-feeders")
	public List<ModeratingFeederDto> getModeratingFeeders() {
		return feederService.getModeratingFeeders();
	}

	@PutMapping("/moderating-feeders/save")
	public void updateModeratingFeeders(@Valid @RequestParam(name = "moderated") List<ModeratingFeederDto> moderatedFeeders) {
		feederService.updateFeedersStatuses(moderatedFeeders);
	}

	@GetMapping("/users")
	public List<User> getALlUsers() {
		return userService.getUsers();
	}

	@PostMapping("/users/add")
	public void addUser(@Valid @RequestBody CreateUserDTO dto) {
		userService.addUser(dto);
	}

	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable Long id) {
		userService.deleteUser(id);
	}
}
