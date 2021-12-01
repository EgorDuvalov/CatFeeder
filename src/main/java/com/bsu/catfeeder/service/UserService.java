package com.bsu.catfeeder.service;

import com.bsu.catfeeder.dto.CreateUserDTO;
import com.bsu.catfeeder.entity.User;
import com.bsu.catfeeder.mapper.UserMapper;
import com.bsu.catfeeder.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class UserService {
	private final UserRepository userRepository;
	private final UserMapper userMapper;

	public void login(String username) {
		if (userRepository.existsByUsername(username)) {
			throw new ResponseStatusException(
				HttpStatus.CONFLICT,
				format("User with id %s already exists", username));
		}
	}

	public List<User> getUsers() {
		return userRepository.findAll();
	}

	@Transactional
	public void addUser(CreateUserDTO dto) {
		if (userRepository.existsByUsername(dto.getUsername())) {
			throw new ResponseStatusException(HttpStatus.CONFLICT,
				format("User with username %s already exists", dto.getUsername()));
		}

		User user = userMapper.mapToEntity(dto);
		userRepository.save(user);
	}

	@Transactional
	public void deleteUser(Long id) {
		if (!userRepository.existsById(id)) {
			throw new ResponseStatusException(
				HttpStatus.NOT_FOUND,
				format("User with id %d doesn't exists", id));
		}

		userRepository.deleteById(id);
	}

	public User retrieveUser(Long id) {
		Optional<User> user = userRepository.findById(id);

		if(user.isEmpty()) {
			throw new ResponseStatusException(
				HttpStatus.NOT_FOUND,
				format("User with id %d doesn't exists", id));
		}

		return user.get();
	}
}
