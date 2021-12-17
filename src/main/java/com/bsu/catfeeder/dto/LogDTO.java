package com.bsu.catfeeder.dto;

import com.bsu.catfeeder.entity.Log;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class LogDTO {
	private Long id;
	private String username;
	private String message;
	private Log.Status status;
	private LocalDateTime creationTime;
}
