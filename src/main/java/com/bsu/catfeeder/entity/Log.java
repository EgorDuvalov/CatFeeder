package com.bsu.catfeeder.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Data
@Table(name = "logs")
public class Log {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	private String message;

	@Column(name = "stack_trace")
	private String stackTrace;

	@Column(name = "created")
	@CreationTimestamp
	private Date creationTime;

	@Enumerated(EnumType.STRING)
	private Status status;

	public enum Status {
		INFO, WARN, ERROR
	}
}
