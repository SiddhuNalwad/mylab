/**
 * Copyright 2017, FMR LLC.
 * All Rights Reserved.
 * Fidelity Confidential Information
 */
package net.stn.streams;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;

/**
 * @author a365143
 *
 */
@Slf4j
public class TraderExercise {

	public static void main(String[] args) {

		Trader raoul = new Trader("Raoul", "Cambridge");
		Trader mario = new Trader("Mario", "Milan");
		Trader alan = new Trader("Alan", "Cambridge");
		Trader brian = new Trader("Brian", "Cambridge");
		List<Transaction> transactions = Arrays.asList(
				new Transaction(brian, 2011, 300),
				new Transaction(raoul, 2012, 1000),
				new Transaction(raoul, 2011, 400),
				new Transaction(mario, 2012, 710),
				new Transaction(mario, 2012, 700),
				new Transaction(alan, 2012, 950));

		// 1. Find all transactions in the year 2011 and sort them by value (small to high).
		List<Transaction> list = transactions.stream().filter(t -> t.getYear() == 2011)
				.sorted((t1, t2) -> (t1.getValue() < t2.getValue()) ? -1 : 1).collect(Collectors.toList());

		list.stream().forEach(t -> log.debug("year:{}\tvalue:{}", t.getYear(), t.getValue()));

		log.debug("-----------------------------");

		List<Transaction> list2 = transactions.stream().filter(t -> t.getYear() == 2011)
				.sorted(Comparator.comparing(Transaction::getValue).reversed()).collect(Collectors.toList());

		list2.stream().forEach(t -> log.debug("year:{}\tvalue:{}", t.getYear(), t.getValue()));

		log.debug("=================================================================");

		// 2. What are all the unique cities where the traders work?
		List<String> list3 = transactions.stream().map(Transaction::getTrader).map(Trader::getCity).distinct()
				.collect(Collectors.toList());
		list3.stream().forEach(c -> log.debug("city:{}", c));

		log.debug("-----------------------------");

		List<String> list4 = transactions.stream().map(t -> t.getTrader().getCity()).distinct()
				.collect(Collectors.toList());
		list4.stream().forEach(c -> log.debug("city:{}", c));

		log.debug("=================================================================");

		// 3. Find all traders from Cambridge and sort them by name.
		List<Trader> list5 = transactions.stream().map(t -> t.getTrader()).filter(t -> t.getCity().equals("Cambridge"))
				.sorted(Comparator.comparing(Trader::getName)).collect(Collectors.toList());

		list5.stream().forEach(t -> log.debug("name:{}\tcity:{}", t.getName(), t.getCity()));
		log.debug("=================================================================");

		// 4. Return a string of all traders’ names sorted alphabetically.
		String allNames = transactions.stream().map(t -> t.getTrader().getName()).distinct().sorted().reduce("",
				(n1, n2) -> n1 + "-" + n2);
		log.debug("allNames:{}", allNames);
		log.debug("=================================================================");

		// 5. Are any traders based in Milan?
		boolean isMilanBased = transactions.stream().map(t -> t.getTrader().getCity()).anyMatch(c -> c.equals("Milan"));
		log.debug("isMilanBased:{}", isMilanBased);
		log.debug("=================================================================");

		// 6. Print all transactions’ values from the traders living in Cambridge.
		transactions.stream().filter(tx -> "Cambridge".equals(tx.getTrader().getCity())).map(Transaction::getValue)
				.forEach(System.out::println);
		log.debug("=================================================================");

		// 7. What’s the highest value of all the transactions?
		Optional<Integer> maxTxValue = transactions.stream().map(Transaction::getValue).reduce(Integer::max);
		maxTxValue.ifPresent(System.out::println);
		log.debug("=================================================================");
		// 8. Find the transaction with the smallest value.
		Optional<Integer> minTxValue = transactions.stream().map(tx -> tx.getValue()).reduce(Integer::min);
		minTxValue.ifPresent(System.out::println);
	}
}
