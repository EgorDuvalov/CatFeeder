package com.bsu.catfeeder.service;

import com.bsu.catfeeder.entity.Feeder;
import com.bsu.catfeeder.entity.User;
import com.bsu.catfeeder.logger.Logger;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class CatService {
	private static final long ONE_MEAL_SIZE = 200;
	private static boolean ACTIVE = false;
	private static Long OWNER_ID = 1L;

	private final FeederActionService feederActionService;
	private final UserService userService;
	private final Logger logger;

	public void releaseDaCat(Long ownerId) {
		OWNER_ID = ownerId;
		ACTIVE = true;
	}

	public void catchDaCat(Long ownerId) {
		ACTIVE = false;
	}

	@Scheduled(fixedRate = 4000L)
	@Transactional
	public void findSomeGoodies() {
		if (ACTIVE) {
			User owner = userService.retrieveUser(OWNER_ID);
			List<Feeder> activeFeeders = owner.getFeeders()
				.stream()
				.filter(Feeder::isActive)
				.collect(Collectors.toList());

			Long remainder = ONE_MEAL_SIZE;
			for (Feeder feeder : activeFeeders) {
				remainder = feederActionService.eatFromFeeder(feeder, remainder);
				if (remainder.equals(0L)) {
					logger.info(owner,
						format("Cat ate from the feeder %d and is hungry no more! (For now)", feeder.getId()));
					return;
				}
				logger.info(owner,
					format("Cat ate from the feeder %d and wants more! Searching for other goodies...", feeder.getId()));
			}
			logger.warn(owner,
				"No more food's left in feeders! Cat is hungry! Do something or we gonna call the police!");
		}
	}
}
