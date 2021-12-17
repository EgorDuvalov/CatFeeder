package com.bsu.catfeeder.service;

import com.bsu.catfeeder.dto.LogDTO;
import com.bsu.catfeeder.entity.Log;
import com.bsu.catfeeder.logger.Logger;
import com.bsu.catfeeder.mapper.LogMapper;
import com.bsu.catfeeder.repository.LogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Optional;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class LogService {
	private final LogRepository logRepository;
	private final LogMapper logMapper;
	private final Logger logger;

	@Value("${logging.expiration-time.months}")
	private Long monthsBeforeExpiry;

	public List<LogDTO> getAllLogs() {
		List<Log> logs = logRepository.findAll();
		return logMapper.mapToDtoList(logs);
	}

	public List<LogDTO> getUserLogs(Long userId) {
		List<Log> logs = logRepository.findAllByUserId(userId);
		return logMapper.mapToDtoList(logs);
	}

	public String getStackTrace(Long logId) {
		Log log = retrieveLog(logId);
		return log.getStackTrace();
	}

	@Scheduled(cron = "0 3 15 * *") //At 3:00 AM on 15th of every month
	private void cleanUpOldLogs() {
		LocalDate dateOfExpiry = LocalDate.now().minusMonths(monthsBeforeExpiry);

		logRepository.deleteAllByCreationTimeBefore(toDate(dateOfExpiry));
		logger.info(null, format("CLEAN-UP. Logs before %s where removed", dateOfExpiry));
	}

	private Log retrieveLog(Long logId) {
		Optional<Log> log = logRepository.findById(logId);
		if (log.isEmpty()) {
			ResponseStatusException e = new ResponseStatusException(HttpStatus.NOT_FOUND, "");
			logger.error(null,
				format("Failed to retrieve stack trace of log %d because log is not found", logId),
				Arrays.toString(e.getStackTrace()));
			throw e;
		}

		return log.get();
	}

	private Date toDate(LocalDate localDate) {
		return Date.from(localDate
			.atStartOfDay(ZoneId.systemDefault())
			.toInstant());
	}
}
