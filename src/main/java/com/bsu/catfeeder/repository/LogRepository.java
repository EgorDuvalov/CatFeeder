package com.bsu.catfeeder.repository;

import com.bsu.catfeeder.entity.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface LogRepository extends JpaRepository<Log, Long> {

	List<Log> findAllByUserId(Long userId);

	void deleteAllByCreationTimeBefore(Date date);
}
