package net.stn.generics;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GenericsExercises {

	public static void main(String[] args) {

		// Q.No.1
		final List<Integer> listInts = Arrays.asList(1, 12, 3, 14, 5, 26, 17, 8, 19, 10, 11, 12, 13);
		final Predicate<Integer> evenPredicate = n -> (n % 2 == 0);
		final Predicate<Integer> oddPredicate = n -> (n % 2 != 0);
		log.debug("Count of evens:{}", countIf(listInts, evenPredicate));
		log.debug("Count of odds:{}", countIf(listInts, oddPredicate));

		// Q.No.2
		Object[] intArray = listInts.toArray();
		log.debug("before swap:[0]={}, [{}]={}", intArray[0], intArray.length - 1, intArray[intArray.length - 1]);
		swap(intArray, 0, intArray.length - 1);
		log.debug("after swap:[0]={}, [{}]={}", intArray[0], intArray.length - 1, intArray[intArray.length - 1]);

		// Q.No.3:
		log.debug("max={}", max(listInts, 1, 4));
	}

	/**
	 * Q.No.1:Write a generic method to count the number of elements in a
	 * collection that have a specific property (for example, odd integers,
	 * prime numbers, palindromes).
	 * 
	 * @param <T>
	 * @param list
	 * @param predicate
	 * @return
	 */
	public static <T extends Number> long countIf(final Collection<T> list, final Predicate<T> predicate) {
		return list.stream().filter(predicate).count();
	}

	/**
	 * Q.No.2:Write a generic method to exchange the positions of two different
	 * elements in an array.
	 * 
	 * @param <T>
	 * @param array
	 * @param oldIndex
	 * @param newIndex
	 */
	public static <T> void swap(T[] array, int oldIndex, int newIndex) {
		// implementation
		T temp = array[oldIndex];
		array[oldIndex] = array[newIndex];
		array[newIndex] = temp;
	}

	/**
	 * Q.No.3:Write a generic method to find the maximal element in the range
	 * [begin, end] of a list.
	 */
	public static <T extends Object & Comparable<? super T>> T max(List<? extends T> list, int begin, int end) {
		int startIndex = begin;
		T maxElement = list.get(begin);
		for (++startIndex; startIndex < end; ++startIndex) {
			if (maxElement.compareTo(list.get(startIndex)) < 0) {
				maxElement = list.get(startIndex);
			}
		}
		return maxElement;
	}
}
