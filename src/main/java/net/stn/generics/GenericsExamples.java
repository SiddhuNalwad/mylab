package net.stn.generics;

import java.util.Arrays;
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
		// the below statement works in JDK8 while it fails in JDK7 as type
		// inference scope has been increased to include method arguments and
		// returns.
		processListString(Collections.emptyList());

		// example 4: Upper bounded wildcards
		log.debug("Sum#1:{}", getSum(Arrays.asList(1, 2, 3, 4, 5, 6)));
		log.debug("Sum#2:{}", getSum(Arrays.asList(1.2, 2.3, 4.5)));

		// example 5: Unbounded wildcards
		printList(Arrays.asList(1, 2, 3));

		// example 6: Lower bounded wildcards
		addNumbers(Arrays.asList(1, 2, 3));
		addNumbers(Arrays.asList(1.2, 2.2, 3.3));

	}

	public static void processListString(List<String> ls) {
		log.debug("inside processListString");
	}

	public static <T> double getSum(List<? extends Number> list) {
		// List is the JDK is defined as follows...
		// public interface List<E>{
		double sum = 0.0;
		for (Number number : list) {
			sum += number.doubleValue();
		}
		return sum;
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

	/**
	 * Unbounded wildcard method
	 * 
	 * @param list
	 */
	public static void printList(List<?> list) {
		list.stream().forEach(System.out::println);
	}

	/**
	 * Lower bounded wildcard method
	 * 
	 * @param list
	 */
	public static void addNumbers(List<? super Integer> list) {
		for (int i = 0; i <= 10; ++i) {
			list.add(i);
		}
	}
}
