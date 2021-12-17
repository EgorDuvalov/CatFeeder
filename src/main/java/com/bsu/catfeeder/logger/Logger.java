package com.bsu.catfeeder.logger;

import com.bsu.catfeeder.entity.Log;
import com.bsu.catfeeder.entity.User;
import com.bsu.catfeeder.repository.LogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.bsu.catfeeder.entity.Log.*;

@Service
@RequiredArgsConstructor
public class Logger {
	private final LogRepository logRepository;

	public void info(User user, String message) {
		createLog(user, message, Status.INFO, null);
	}

	public void warn(User user, String message) {
		createLog(user, message, Status.WARN, null);
	}

	public void error(User user, String message, String reason) {
		createLog(user, message, Status.ERROR, reason);
	}

	private void createLog(User user, String message, Status status, String reason) {
		Log log = new Log();
		log.setUser(user);
		log.setMessage(message);
		log.setStatus(status);
		log.setReason(reason);

		logRepository.save(log);
	}
}
