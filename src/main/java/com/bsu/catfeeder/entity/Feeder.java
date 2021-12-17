package com.bsu.catfeeder.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.CascadeType;
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

@Entity
@Data
@Table(name = "feeders")
public class Feeder {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@EqualsAndHashCode.Exclude @ToString.Exclude
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@Column(nullable = false)
	private String name;

	private boolean active;

	private long capacity;

	@Column(name = "load")
	private long actualLoad;

	@OneToOne
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
