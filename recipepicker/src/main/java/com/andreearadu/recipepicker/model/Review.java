package com.andreearadu.recipepicker.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

enum NoOfStars {
	STARS_5(5), STARS_4(4), STARS_3(3), STARS_2(2), STARS_1(1);

	@SuppressWarnings("unused")
	private final int number;

	private NoOfStars(int number) {
		this.number = number;
	}

}

@Entity
@Table(name = "review")
public class Review implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "description")
	@NotNull
	private String description;

	@Id
	@Enumerated(EnumType.STRING)
	private NoOfStars noOfStars;

	@ManyToOne
	private Recipe recipe;

}
