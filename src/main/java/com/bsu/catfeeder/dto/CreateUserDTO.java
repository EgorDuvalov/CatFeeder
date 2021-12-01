package com.bsu.catfeeder.dto;

import com.bsu.catfeeder.entity.User;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CreateUserDTO {
	@NotNull
	private String username;
	private User.Role role;
}
