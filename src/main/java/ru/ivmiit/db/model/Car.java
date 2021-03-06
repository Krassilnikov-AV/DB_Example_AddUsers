/*
 * Copyright (c) 2022 Tander, All Rights Reserved.
 */

package ru.ivmiit.db.model;

/**
 * Класс Car
 */
public class Car {
	private Integer id;
	private String model;
	private User owner;

	public Car() {
	}

	public Car(Integer id, String model, User owner) {
		this.id = id;
		this.model = model;
		this.owner = owner;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}
}