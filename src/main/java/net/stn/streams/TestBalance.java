package net.stn.streams;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

/**
 * @author a365143
 *
 */
public class TestBalance {
	public static void main(String[] args) {

		System.out.println("Testing Balances");

		BalancesOverTimeDTO dto1 = new BalancesOverTimeDTO("A1", LocalDate.of(2017, 10, 1), BigDecimal.valueOf(100.00));
		BalancesOverTimeDTO dto2 = new BalancesOverTimeDTO("A2", LocalDate.of(2017, 10, 1), BigDecimal.valueOf(200.00));
		BalancesOverTimeDTO dto3 = new BalancesOverTimeDTO("A3", LocalDate.of(2017, 10, 1), BigDecimal.valueOf(300.00));

		BalancesOverTimeDTO dto4 = new BalancesOverTimeDTO("A1", LocalDate.of(2017, 10, 2), BigDecimal.valueOf(200.00));
		BalancesOverTimeDTO dto5 = new BalancesOverTimeDTO("A2", LocalDate.of(2017, 10, 2), BigDecimal.valueOf(400.00));

		BalancesOverTimeDTO dto6 = new BalancesOverTimeDTO("A3", LocalDate.of(2017, 10, 3), BigDecimal.valueOf(900.00));

		List<BalancesOverTimeDTO> balances = Arrays.asList(dto1, dto2, dto3, dto4, dto5, dto6);

		Map<LocalDate, List<BalancesOverTimeDTO>> accountBalancesMap = balances.stream()
				.collect(Collectors.groupingBy(BalancesOverTimeDTO::getDate));

		System.out.println(accountBalancesMap);

		// accountBalancesMap.entrySet().stream().collect(ArrayList::new,
		// TestBalance::biFunction, ArrayList::addAll);

	}

	static List<Balance> biFunction(Entry<LocalDate, List<BalancesOverTimeDTO>> entry) {
		List<BalancesOverTimeDTO> dtos = entry.getValue();
		Double sum = dtos.stream().collect(Collectors.summingDouble(TestBalance::getBalance));
		System.out.println("sum:" + sum);
		entry.getKey();
		return null;
	}

	static class BalancesOverTimeDTO {

		public BalancesOverTimeDTO(String accountId, LocalDate date, BigDecimal balance) {
			this.accountId = accountId;
			this.date = date;
			this.balance = balance;
		}

		/**
		 * @return the accountId
		 */
		public String getAccountId() {
			return accountId;
		}

		/**
		 * @param accountId
		 *            the accountId to set
		 */
		public void setAccountId(String accountId) {
			this.accountId = accountId;
		}

		/**
		 * @return the balance
		 */
		public BigDecimal getBalance() {
			return balance;
		}

		/**
		 * @param balance
		 *            the balance to set
		 */
		public void setBalance(BigDecimal balance) {
			this.balance = balance;
		}

		/**
		 * @return the date
		 */
		public LocalDate getDate() {
			return date;
		}

		/**
		 * @param date
		 *            the date to set
		 */
		public void setDate(LocalDate date) {
			this.date = date;
		}

		/** account number */
		private String accountId;

		/** balance. */
		private BigDecimal balance;

		/** Date. */
		private LocalDate date;

	}

	static class Balance {
		/**
		 * @return the balance
		 */
		public Double getBalance() {
			return balance;
		}

		/**
		 * @param balance
		 *            the balance to set
		 */
		public void setBalance(Double balance) {
			this.balance = balance;
		}

		/**
		 * @return the date
		 */
		public LocalDate getDate() {
			return date;
		}

		/**
		 * @param date
		 *            the date to set
		 */
		public void setDate(LocalDate date) {
			this.date = date;
		}

		private Double balance;
		private LocalDate date;
	}

	private static double getBalance(BalancesOverTimeDTO dto) {
		return dto.getBalance().doubleValue();
	}
}
