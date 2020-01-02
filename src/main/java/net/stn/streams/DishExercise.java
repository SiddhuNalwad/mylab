/**
 * Copyright 2017, FMR LLC.
 * All Rights Reserved.
 * Fidelity Confidential Information
 */
package net.stn.streams;

import java.util.Arrays;
import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;

/**
 * @author a365143
 *
 */
@Slf4j
public class DishExercise {
	enum CaloricLevel {
		DIET, NORMAL, FAT
	};

	public static void main(String[] args) {

		List<Dish> menu = Arrays.asList(new Dish("pork", false, 800, Dish.Type.MEAT),
				new Dish("beef", false, 700, Dish.Type.MEAT), new Dish("chicken", false, 400, Dish.Type.MEAT),
				new Dish("french fries", true, 530, Dish.Type.OTHER), new Dish("rice", true, 350, Dish.Type.OTHER),
				new Dish("season fruit", true, 120, Dish.Type.OTHER), new Dish("pizza", true, 550, Dish.Type.OTHER),
				new Dish("prawns", false, 300, Dish.Type.FISH), new Dish("salmon", false, 450, Dish.Type.FISH));

		// 1. Find total number of dishes in the menu
		long totalDishes = menu.stream().collect(Collectors.counting());
		log.debug("totalDishes:{}", totalDishes);

		// 2. Find dish with maximum calories
		Comparator<Dish> caloriesCompartor = Comparator.comparingInt(Dish::getCalories); // T->int
		Optional<Dish> maxCalDish = menu.stream().collect(Collectors.maxBy(caloriesCompartor));
		if (maxCalDish.isPresent()) {
			Dish dish = maxCalDish.get();
			log.debug("DishName:{}\tCalories:{}", dish.getName(), dish.getCalories());
		}

		// 3. Find average of calories
		double avgCal = menu.stream().collect(Collectors.averagingDouble(Dish::getCalories));
		log.debug("average calories:{}", avgCal);

		// 4. Get statistics of the menu
		IntSummaryStatistics summary = menu.stream().collect(Collectors.summarizingInt(Dish::getCalories));
		log.debug("summary:{}", summary);

		// 5. joining
		// menu.stream().collect(Collectors.joining()); //not working - code
		// from Java 8 in action
		String shortMenu = menu.stream().map(Dish::getName).collect(Collectors.joining(","));
		log.debug("shortMenu:{}", shortMenu);

		// 6. Group dishes by type (MEAT,FISH or OTHER)
		Map<Dish.Type, List<Dish>> dishesByType = menu.stream().collect(Collectors.groupingBy(Dish::getType));
		log.debug("dishTypes:{}", dishesByType);

		// 7. Group dishes by DIET, NORMAL or FAT
		Map<CaloricLevel, List<Dish>> dishesByCaloricLevel = menu.stream().collect(Collectors.groupingBy(dish -> {
			if (dish.getCalories() <= 400)
				return CaloricLevel.DIET;
			else if (dish.getCalories() <= 700)
				return CaloricLevel.NORMAL;
			else
				return CaloricLevel.FAT;
		}));

		log.debug("dishesByCaloricLevel:{}", dishesByCaloricLevel);

		// 8. Get total calories of menu
		// IST METHOD
		final int totalCaloriesOfMenu1 = menu.stream().collect(Collectors.summingInt(Dish::getCalories));
		log.debug("totalCaloriesOfMenu:{} calories", totalCaloriesOfMenu1);

		// IIND METHOD
		final int totalCaloriesOfMenu2 = menu.stream().mapToInt(Dish::getCalories).sum();
		log.debug("totalCaloriesOfMenu:{} calories", totalCaloriesOfMenu2);

		// 9. Whether all dishes in the menu are high calories (greater than 300
		// calories)
		boolean highCalorieMenuFlag = menu.stream().allMatch(d -> d.getCalories() > 300);
		if (highCalorieMenuFlag) {
			log.debug("It is HIGH calorie menu.");
		} else {
			log.debug("It is LOW calorie menu.");
		}

		// 10. Get all low calorie dishes from the menu (calorie <= 300)
		final List<Dish> lowCalorieDishes = menu.stream().filter(d -> d.getCalories() <= 300)
				.collect(Collectors.toList());
		lowCalorieDishes.stream().forEach(d -> log.debug("LOW calorie dish:{}", d));
	}
}
