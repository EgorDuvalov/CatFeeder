package com.bsu.catfeeder.repository;

import com.bsu.catfeeder.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}
