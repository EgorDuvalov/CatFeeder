package com.bsu.catfeeder.dto;

import com.bsu.catfeeder.entity.Feeder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class ModeratingFeederDto {
	@NotNull
	private Long id;
	private String name;
	private String username;
	@NotNull
	private Feeder.Status status;

	public ModeratingFeederDto(Feeder feeder) {
		this.id = feeder.getId();
		this.name = feeder.getName();
		this.username = feeder.getUser().getUsername();
		this.status = feeder.getStatus();
	}
}
