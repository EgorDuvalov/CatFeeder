package com.bsu.catfeeder.repository;

import com.bsu.catfeeder.entity.Feeder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeederRepository extends JpaRepository<Feeder, Long> {

	List<Feeder> findAllByStatus(Feeder.Status status);

	List<Feeder> findAllByScheduleId(Long scheduleId);

	List<Feeder> findAllByTypeAndActiveTrue(Feeder.Type type);
}
