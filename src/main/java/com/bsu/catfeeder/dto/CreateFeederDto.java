package com.bsu.catfeeder.dto;

import com.bsu.catfeeder.entity.Feeder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CreateFeederDto {
	@NotBlank(message = "Name is mandatory")
	private String name;
	@NotNull(message = "Type is required")
	private Feeder.Type type;
	@NotNull(message = "Capacity has to be specified")
	@Min(value = 1L, message = "Capacity has to be greater than zero")
	private Long capacity;
}
