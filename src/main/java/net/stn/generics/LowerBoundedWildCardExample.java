package net.stn.generics;

import java.util.ArrayList;
import java.util.List;

public class LowerBoundedWildCardExample {

	public static void main(String[] args) {
		List<Integer> listInts = new ArrayList<>();
		addNumbers(listInts);
		print(listInts);

		List<Object> listNums = new ArrayList<>();
		listNums.add("One");
		listNums.add("Two");
		addNumbers(listNums); // appends 10,20,30...after "One","Two".
		print(listNums);
	}

	public static void addNumbers(List<? super Integer> list) {
		for (int i = 1; i < 10; i++) {
			list.add(i * 10);
		}
	}

	public static void print(List<? super Integer> list) {
		list.stream().forEach(System.out::println);
	}
}
