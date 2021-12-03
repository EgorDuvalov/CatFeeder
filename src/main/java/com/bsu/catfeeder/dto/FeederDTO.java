package com.bsu.catfeeder.dto;

import com.bsu.catfeeder.entity.Feeder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FeederDTO {
	private Long id;
	private String name;
	private String owner;
	private String schedule;
	private Boolean active;
	private Long capacity;
	private Long actualLoad = 0L;
	private Feeder.Type type;
	private Feeder.Status status;
}
