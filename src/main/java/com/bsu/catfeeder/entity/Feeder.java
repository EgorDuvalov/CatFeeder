package com.bsu.catfeeder.entity;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
@Data
public class Feeder {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@Column(name = "user_id")
	private User user;

	@Column(nullable = false)
	private String name;

	private boolean active;

	private long capacity;

	private long actualLoad;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "schedule_id")
	private Schedule schedule;

	@Enumerated(EnumType.STRING)
	private Status status;

	public enum Status {
		REJECTED, MODERATING, ACCEPTED
	}

	@Enumerated(EnumType.STRING)
	private Type type;

	public enum Type {
		SCALES, TIMER
	}
}
