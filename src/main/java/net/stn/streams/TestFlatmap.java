package net.stn.streams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author a365143
 *
 */
public class TestFlatmap {
	public static void main(String[] args) {
		List<String> list1 = Arrays.asList("10", "20", "30");
		List<String> list2 = Arrays.asList("40", "50", "60", "70");

		List<List<String>> list = new ArrayList<>();
		list.add(list1);
		list.add(list2);

		List<String> flatList = list.stream().flatMap(l -> l.stream().map(s -> s)).collect(Collectors.toList());
		flatList.stream().forEach(System.out::println);

	}
}
