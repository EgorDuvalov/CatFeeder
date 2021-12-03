package com.bsu.catfeeder.dto;

import lombok.Getter;
import lombok.Setter;
import org.joda.time.LocalTime;

@Getter
@Setter
public class ScheduleDTO {
	private Long id;
	private String name;
	private LocalTime starts;
	private LocalTime ends;
	private Integer interval;
}
