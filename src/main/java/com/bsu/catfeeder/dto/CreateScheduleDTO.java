package com.bsu.catfeeder.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalTime;

@Getter
@Setter
public class CreateScheduleDTO {

	@NotBlank(message = "Name must not be blank")
	private String name;

	@DateTimeFormat(pattern = "HH:mm")
	@NotNull(message = "Time of schedule starting must be specified")
	private LocalTime starts;

	@DateTimeFormat(pattern = "HH:mm")
	@NotNull(message = "Time of schedule ending must be specified")
	private LocalTime ends;

	@NotNull(message = "Interval of feeding must be specified")
	private Integer interval;
}
