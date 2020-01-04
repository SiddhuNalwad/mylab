package net.stn.generics;

import java.util.Collections;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GenericsExamples {

	public static void main(String[] args) {

		// example 1: invoking generic method
		Integer[] intArray = { 12, 45, 6, 8, 9, 1 };
		int occurs = compareGreaterThan(intArray, 9);
		log.debug("occurs:{}", occurs);

		// example 2:
		Container<Number> container = new Container<>();
		container.setContent(Integer.valueOf(10)); // adding Integer (sub type
													// of Number) content
		container.setContent(Double.valueOf(10.75)); // adding Double (sub type
														// of Number) content

		// example 3:
		// type inference will decide the target type as List<String> based on
		// the below statement.
		List<String> listString1 = Collections.emptyList();
		// using type witness
		List<String> listString2 = Collections.<String>emptyList();

		// the below statement works
		processListString(listString1);
		// the below statement works in JDK8 while it fails in JDK7
		processListString(Collections.emptyList());
	}

	public static void processListString(List<String> ls) {
		log.debug("inside processListString");
	}

	/**
	 * This is a generic method with bounded type.
	 * 
	 * @param <T>
	 *            generic type bounded by Comparable type.
	 * @param anArray
	 * @param givenElement
	 * @return
	 */
	public static <T extends Comparable<T>> int compareGreaterThan(T[] anArray, T givenElement) {
		int occurrence = 0;
		for (T element : anArray) {
			if (element.compareTo(givenElement) > 0) {
				++occurrence;
			}
		}
		return occurrence;
	}

	public static class Container<T> {
		T content;

		void setContent(final T givenContent) {
			content = givenContent;
		}

		T getContent() {
			return content;
		}
	}
}
