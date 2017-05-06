/**
 * Copyright 2017, FMR LLC.
 * All Rights Reserved.
 * Fidelity Confidential Information
 */
package net.stn.streams;

import lombok.extern.slf4j.Slf4j;

/**
 * The Class Dish.
 *
 * @author a365143
 */
@Slf4j
public class Dish {

	/** The name. */
	private final String name;

	/** The vegetarian. */
	private final boolean vegetarian;

	/** The calories. */
	private final int calories;

	/** The type. */
	private final Type type;

	/**
	 * Instantiates a new dish.
	 *
	 * @param name
	 *            the name
	 * @param vegetarian
	 *            the vegetarian
	 * @param calories
	 *            the calories
	 * @param type
	 *            the type
	 */
	public Dish(String name, boolean vegetarian, int calories, Type type) {
		log.debug("Dish ctor");
		this.name = name;
		this.vegetarian = vegetarian;
		this.calories = calories;
		this.type = type;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Checks if is vegetarian.
	 *
	 * @return true, if is vegetarian
	 */
	public boolean isVegetarian() {
		return vegetarian;
	}

	/**
	 * Gets the calories.
	 *
	 * @return the calories
	 */
	public int getCalories() {
		return calories;
	}

	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public Type getType() {
		return type;
	}

	/**
	 * 
	 * <p>
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return name;
	}

	/**
	 * The Enum Type.
	 */
	public enum Type {

		/** The meat. */
		MEAT,
		/** The fish. */
		FISH,
		/** The other. */
		OTHER
	}
}