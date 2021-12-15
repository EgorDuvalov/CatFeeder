package com.bsu.catfeeder.service;

import com.bsu.catfeeder.dto.CreateUserDTO;
import com.bsu.catfeeder.entity.User;
import com.bsu.catfeeder.mapper.UserMapper;
import com.bsu.catfeeder.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class UserService {
	private final static String BASE_URL = "http://localhost:8080/catFeeder";
	private final static String REDIRECT_ADMIN_URL = "forward:" + BASE_URL + "/admin";
	private final static String REDIRECT_CLIENT_URL = "forward:" + BASE_URL + "/%d/feeders";
	private final UserRepository userRepository;
	private final UserMapper userMapper;
	private final Logger logger;

	public String login(String username) {
		User user = retrieveUser(username);
		if (user.getRole().equals(User.Role.ADMIN)) {
			logger.info(format("Admin %s login", username));
			return REDIRECT_ADMIN_URL;
		}

		logger.info(format("User %s login", username));
		return format(REDIRECT_CLIENT_URL, user.getId());
	}

	public List<User> getUsers() {
		return userRepository.findAll();
	}

	@Transactional
	public void addUser(CreateUserDTO dto) {
		if (userRepository.existsByUsername(dto.getUsername())) {
			ResponseStatusException e = new ResponseStatusException(HttpStatus.CONFLICT,
				format("User with username %s already exists", dto.getUsername()));
			logger.warn("Creation attempt failed for" + dto.getUsername() + "\n" + Arrays.toString(e.getStackTrace()));
			throw e;
		}

		User user = userMapper.mapToEntity(dto);
		userRepository.save(user);
		logger.info(format("Creation attempt successful for %s", dto.getUsername()));
	}

	@Transactional
	public void deleteUser(Long id) {
		if (!userRepository.existsById(id)) {
			ResponseStatusException e = new ResponseStatusException(
				HttpStatus.NOT_FOUND,
				format("User with id %d doesn't exist", id));
			logger.warn("Delete attempt failed for" + id + "\n" + Arrays.toString(e.getStackTrace()));
			throw e;
		}
		logger.info("Delete attempt successful for" + id);
		userRepository.deleteById(id);
	}

	public User retrieveUser(Long id) {
		Optional<User> user = userRepository.findById(id);
		if (user.isEmpty()) {
			ResponseStatusException e = new ResponseStatusException(
				HttpStatus.NOT_FOUND,
				format("User with id %d doesn't exist", id));
			logger.warn("Retrieve attempt failed for" + id + "\n" + Arrays.toString(e.getStackTrace()));
			throw e;
		}
		logger.info("Retrieve attempt successful for" + id);
		return user.get();
	}

	public User retrieveUser(String username) {
		Optional<User> user = userRepository.findByUsername(username);
		if (user.isEmpty()) {
			ResponseStatusException e = new ResponseStatusException(
				HttpStatus.NOT_FOUND,
				format("User with id %s doesn't exist", username));
			logger.warn("Retrieve attempt failed for" + username + "\n" + Arrays.toString(e.getStackTrace()));
			throw e;
		}

		logger.info(format("Retrieve attempt successful for %s", username));
		return user.get();
	}
}
