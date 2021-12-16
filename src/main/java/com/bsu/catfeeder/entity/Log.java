package com.bsu.catfeeder.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "logs")
@NoArgsConstructor
public class Log {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "creation_time")
    private LocalDateTime creationTime;
    @Column(name = "message")
    private String message;

    public Log(LocalDateTime creationTime, String message) {
        this.creationTime = creationTime;
        this.message = message;
    }
}
