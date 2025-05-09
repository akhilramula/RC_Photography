package com.rcphotography.entity;

import java.time.LocalDate;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Photo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String title;
	private String description;
	private String imageUrl;
	private LocalDate dateCreated;
	private LocalDate lastUpdated;

	@PrePersist
	public void onCreate() {
		this.dateCreated = LocalDate.now();
		this.lastUpdated = LocalDate.now();
	}

	@PreUpdate
	public void onUpdate() {
		this.lastUpdated = LocalDate.now();
	}


}
