/**
 * Copyright 2016, FMR LLC.
 * All Rights Reserved.
 * Fidelity Confidential Information
 */
package net.stn;

import java.util.Arrays;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

/**
 * @author a365143
 *
 */
@Slf4j
public class TestLab {

	public static void main(String[] args) {
		listReductionTest();
	}

	private static void listReductionTest() {
		List<String> numbers = Arrays.asList("1", "2", "3");
		String line = numbers.stream().reduce((p1, p2) -> {
			log.debug("p1={}\tp2={}", p1, p2);
			return p1 + "|" + p2;
		}).get();

		log.debug("line={}", line);
	}

}
