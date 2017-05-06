package net.stn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FunctionalPuzzle {

	public static void main(String[] args) {
		// Given a List<Integer> value, for example, {1, 4, 9}, construct a
		// List<List<Integer>> value whose members are all the subsets of
		// {1, 4, 9} — in any order. The
		// subsets of {1, 4, 9} are {1, 4 ,9}, {1, 4}, {1, 9}, {4, 9}, {1}, {4},
		// {9}, and {}.

		List<Integer> numberList = Arrays.asList(1, 4, 9);

		FunctionalPuzzle puzzle = new FunctionalPuzzle();
		List<List<Integer>> result = puzzle.subsets(numberList);

		result.stream().forEach(System.out::println);
	}

	List<List<Integer>> subsets(List<Integer> list) {
		if (list.isEmpty()) {
			List<List<Integer>> answer = new ArrayList<>();
			answer.add(Collections.emptyList());
			return answer;
		}

		// get the first element
		Integer firstElement = list.get(0);
		// create a sub list from remaining elements
		List<Integer> rest = list.subList(1, list.size());

		List<List<Integer>> subAnswer = subsets(rest);
		List<List<Integer>> subAnswer2 = insertAll(firstElement, subAnswer);

		return this.concat(subAnswer, subAnswer2);
	}

	List<List<Integer>> insertAll(Integer first, List<List<Integer>> lists) {
		List<List<Integer>> result = new ArrayList<>();
		for (List<Integer> list : lists) {
			List<Integer> copyList = new ArrayList<>();
			copyList.add(first);
			copyList.addAll(list);
			result.add(copyList);
		}
		return result;
	}

	List<List<Integer>> concat(List<List<Integer>> a, List<List<Integer>> b) {
		List<List<Integer>> r = new ArrayList<>(a);
		r.addAll(b);
		return r;
	}
}
