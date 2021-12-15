package com.bsu.catfeeder.service;

import com.bsu.catfeeder.dto.CreateFeederDto;
import com.bsu.catfeeder.dto.FeederDTO;
import com.bsu.catfeeder.dto.ModeratingFeederDto;
import com.bsu.catfeeder.entity.Feeder;
import com.bsu.catfeeder.entity.User;
import com.bsu.catfeeder.mapper.FeederMapper;
import com.bsu.catfeeder.repository.FeederRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Service
@Transactional
@RequiredArgsConstructor
public class FeederService {
    private final FeederRepository feederRepository;
    private final ScheduleService scheduleService;
    private final FeederMapper feederMapper;
    private final UserService userService;
    private final Logger logger;

    public List<FeederDTO> getFeedersOfUser(Long userId) {
        User owner = userService.retrieveUser(userId);
        List<Feeder> feeders = owner.getFeeders();
        return feederMapper.mapToDtoList(feeders);
    }

    public List<ModeratingFeederDto> getModeratingFeeders() {
        List<Feeder> feeders = feederRepository.findAllByStatus(Feeder.Status.MODERATING);
        return feeders.stream().map(ModeratingFeederDto::new).collect(Collectors.toList());
    }

    public FeederDTO addFeeder(Long userId, CreateFeederDto dto) {
        Feeder feeder = feederMapper.mapToEntity(dto);
        feeder.setUser(userService.retrieveUser(userId));
        feeder.setStatus(Feeder.Status.MODERATING);
        feeder = feederRepository.save(feeder);
        logger.info("Feeder" + feeder + "is moderating" + ", user id " + userId);
        return feederMapper.mapToDto(feeder);
    }

    public void setSchedule(Long userId, Long feederId, Long scheduleId) {
        userService.retrieveUser(userId);
        Feeder feeder = retrieveFeeder(feederId);
        feeder.setSchedule(scheduleService.retrieveSchedule(scheduleId));
        logger.info("Schedule "+scheduleId+" is set for feeder" + feederId);
        feederRepository.save(feeder);
    }

    public void activateFeeder(Long userId, Long feederId) {
        userService.retrieveUser(userId);//Just to check that user exists
        Feeder feeder = retrieveFeeder(feederId);
        checkBeforeActivation(feeder);
        feeder.setActive(true);
        logger.info("Feeder" + feederId + "is now active for user" + userId);
        feederRepository.save(feeder);
    }

    private void checkBeforeActivation(Feeder feeder) {
        if (!feeder.getStatus().equals(Feeder.Status.ACCEPTED)) {
            ResponseStatusException e = new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Feeder should be accepted by admin before getting activated");
            logger.warn("Arrogant try to use feeder "+ feeder.getId() + "\n" + Arrays.toString(e.getStackTrace()));
            throw e;
        }
        if (!feeder.getType().equals(Feeder.Type.TIMER)) {
            if (feeder.getSchedule() == null) {
                ResponseStatusException e = new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "Timer-based feeder requires schedule before getting activated");
                logger.warn("Arrogant try to use timer-based feeder "+ feeder.getId() + "\n" + Arrays.toString(e.getStackTrace()));
                throw e;
            }
        }
    }

    public void deleteFeeder(Long userId, Long feederId) {
        userService.retrieveUser(userId);;//Just to check that user exists
        retrieveFeeder(feederId); //Just to check feeder exists
        logger.info("User "+ feederId + " successfully deleted feeder" + feederId);
        feederRepository.deleteById(feederId);
    }

    public void updateFeedersStatuses(List<ModeratingFeederDto> moderatedFeeders) {
        for (ModeratingFeederDto moderated : moderatedFeeders) {
            Optional<Feeder> feeder = feederRepository.findById(moderated.getId());
            if (feeder.isEmpty()) {
                logger.error(
                        format("Error while updating feeders statuses. Feeder with id %d is not found", moderated.getId()));
            } else {
                Feeder toUpdate = feeder.get();
                toUpdate.setStatus(moderated.getStatus());
                feederRepository.save(toUpdate);
                logger.info("Feeder "+ toUpdate.getId() + " changed his status to "+ moderated.getStatus());
            }
        }
    }

    private Feeder retrieveFeeder(Long id) {
        Optional<Feeder> feeder = feederRepository.findById(id);
        if (feeder.isEmpty()) {
            ResponseStatusException e = new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    format("Feeder with id %d is not found", id));
            logger.warn("Retrieve failed for feeder "+ id + "\n" + Arrays.toString(e.getStackTrace()));
            throw e;
        }
        return feeder.get();
    }
}

