package com.bsu.catfeeder.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class LogDto {
    private LocalDateTime creationTime;
    private String message;
}
