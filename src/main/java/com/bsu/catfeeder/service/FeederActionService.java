package com.bsu.catfeeder.service;

import com.bsu.catfeeder.entity.Feeder;
import com.bsu.catfeeder.logger.Logger;
import com.bsu.catfeeder.repository.FeederRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class FeederActionService {
	private static final long ONE_MEAL_SIZE = 200L;
	private final FeederRepository feederRepository;
	private final Logger logger;

	public Long eatFromFeeder(Feeder feeder, Long amount) {
		Long actualLoad = feeder.getActualLoad();

		long remainder = actualLoad - amount;
		if (remainder >= 0) {
			feeder.setActualLoad(remainder);
			remainder = 0L;
		} else {
			feeder.setActualLoad(0L);
			remainder = -1 * remainder;
		}

		if (feeder.getType().equals(Feeder.Type.SCALES)) {
			refillScalesFeeder(feeder);
		}

		feederRepository.save(feeder);
		return remainder;
	}

	private void refillScalesFeeder(Feeder feeder) {
		double percentage = (double) feeder.getActualLoad() / feeder.getCapacity();
		if (percentage < 0.2D) {
			long increase = new BigDecimal(feeder.getCapacity()).multiply(new BigDecimal("0.7")).longValue();
			feeder.setActualLoad(feeder.getActualLoad() + increase);
			logger.info(feeder.getUser(),
				format("Scales-feeder %d was refilled. Actual load: %d",
					feeder.getId(),
					feeder.getActualLoad()));
		}
	}

	@Scheduled(fixedRate = 9000L)
	@Transactional
	public void refillTimerFeeders() {
		List<Feeder> feeders = feederRepository.findAllByTypeAndActiveTrue(Feeder.Type.TIMER);
		for (Feeder feeder : feeders) {
			feeder.setActualLoad(feeder.getActualLoad() + ONE_MEAL_SIZE);
			if (feeder.getActualLoad() > feeder.getCapacity()) {
				feeder.setActualLoad(feeder.getCapacity());
			}
			logger.info(feeder.getUser(),
				format("Timer-feeder %d was refilled. Actual load: %d",
					feeder.getId(),
					feeder.getActualLoad()));
		}
		feederRepository.saveAll(feeders);
	}
}
