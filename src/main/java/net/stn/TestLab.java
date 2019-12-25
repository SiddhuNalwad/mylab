/**
 * Copyright 2016, FMR LLC.
 * All Rights Reserved.
 * Fidelity Confidential Information
 */
package net.stn;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;

import lombok.extern.slf4j.Slf4j;

/**
 * @author a365143
 *
 */
@Slf4j
public class TestLab {

	public static final String DECIMAL_FORMAT_TWO = "#0.00";

	public static void main(String[] args) {

		System.out.println("===main===");

		// listReductionTest();
		// testScale();
		// System.out.println(stringToTwoPosBigDecimal("123.45543f"));

		List<String> list = new ArrayList<>();
		list.add("test1");
		list.add("test2");
		list.add("test1");
		list.add("");
		list.add(null);

		list.stream().distinct().forEach(item -> System.out.format("%s\n", item));

		printBigInteger();

		// Stream<String> uniqueAccountStream = list.stream()
		// .filter(distinctByKey(p -> p.toString()));
		//
		// System.out.format("uniqueAccountStream:%s", uniqueAccountStream);
		//
		// uniqueAccountStream.forEach(t -> System.out.println(t));
	}

	private static void testScale() {
		BigDecimal b = BigDecimal.valueOf(123.45243f);

		System.out.println("value=" + b);

	}

	private static void listReductionTest() {
		List<String> numbers = Arrays.asList("1", "2", "3");
		String line = numbers.stream().reduce((p1, p2) -> {
			log.debug("p1={}\tp2={}", p1, p2);
			return p1 + "|" + p2;
		}).get();

		log.debug("line={}", line);
	}

	public static BigDecimal stringToTwoPosBigDecimal(final String in) {
		final DecimalFormat formatter = new DecimalFormat(DECIMAL_FORMAT_TWO);
		final Double d = new Double(String.valueOf(in));
		final String fd = formatter.format(d);
		return new BigDecimal(fd);
	}

	public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
		Map<Object, Boolean> seen = new ConcurrentHashMap<>();
		return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
	}

	public static void printBigInteger() {
		BigDecimal big = new BigDecimal(
				"4930592405923095023950238502395487234932493248237502435343423432423423423423423423930592405923095023950238543343434343556436354634563453453534502395");
		System.out.println("big=" + big);
	}
}
