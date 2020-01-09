package net.stn.generics;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;

public class MethodReferenceExamples {
	public static void main(String[] args) {
		final List<Integer> listInts = Arrays.asList(1, 2, 3, 4, 5);
		// 4. METHOD REFERNECE TO CONSTRUCTOR

		// Ist Method
		transferElements(listInts, () -> new HashSet<>());

		// IInd Method (Compact)
		final Set<Integer> setInts = transferElements(listInts, HashSet::new);
		setInts.stream().forEach(System.out::println);
	}

	/**
	 * Write a generic method which copies elements from one collection to
	 * another.
	 * 
	 * @param <T>
	 * @param <SOURCE>
	 * @param <DEST>
	 * @param sourceCollection
	 * @param collectionFactory
	 * @return
	 */
	public static <T, SOURCE extends Collection<T>, DEST extends Collection<T>> DEST transferElements(
			SOURCE sourceCollection, Supplier<DEST> collectionFactory) {

		final DEST dest = collectionFactory.get();
		for (T t : sourceCollection) {
			dest.add(t);
		}
		return dest;
	}
}
