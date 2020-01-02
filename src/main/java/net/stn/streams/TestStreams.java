/**
 * Copyright 2017, FMR LLC.
 * All Rights Reserved.
 * Fidelity Confidential Information
 */
package net.stn.streams;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;

/**
 * @author a365143
 *
 */
@Slf4j
public class TestStreams {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		// flatMap_Example2();
		Using_new_Example3();
	}

	private static void flatMap_Example1() {
		final List<String> words = Arrays.asList("Hello", "Hellw");
		List<String[]> list = words.stream().map(word -> word.split("")).distinct().collect(Collectors.toList());

		log.debug("list:{}", list);
		for (String[] strings : list) {
			for (String string : strings) {
				log.debug("string:{}", string);
			}
		}

		log.debug("=============================================");

		List<String> distinchChars = words.stream().map(w -> w.split("")).flatMap(Arrays::stream).distinct()
				.collect(Collectors.toList());
		for (String string : distinchChars) {
			log.debug("string:{}", string);
		}

	}

	private static void flatMap_Example2() {
		List<Integer> set1 = Arrays.asList(1, 2, 3, 9);
		List<Integer> set2 = Arrays.asList(4, 5, 6);

		List<int[]> set = set1.stream().flatMap(i -> set2.stream().map(j -> new int[] { i, j }))
				.collect(Collectors.toList());
		log.debug("set={}", set);
		set.stream().forEach(a -> {
			String combination = "(";
			for (int i = 0; i < a.length; i++) {
				combination = combination + a[i] + ",";
			}

			log.debug("{}", combination);
		});

	}

	private static void Using_new_Example3() {
		Map<String, FourArgumentFunction<String, Boolean, Integer, Dish.Type, Dish>> dishes = new HashMap<>();
		FourArgumentFunction<String, Boolean, Integer, Dish.Type, Dish> interfaceDish = dishes.put("veg", Dish::new);
		log.debug("interfaceDish:{}", interfaceDish);

		Dish dish = dishes.get("veg").apply("BisiBelleBath", true, 800, Dish.Type.OTHER);
		log.debug("new dish:{}", dish);
	}

}
