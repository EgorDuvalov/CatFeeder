package com.bsu.catfeeder.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
public class ScheduleDTO {
	private Long id;
	private String name;
	@JsonFormat(pattern = "HH:mm")
	private LocalTime starts;
	@JsonFormat(pattern = "HH:mm")
	private LocalTime ends;
	private Integer interval;
}
