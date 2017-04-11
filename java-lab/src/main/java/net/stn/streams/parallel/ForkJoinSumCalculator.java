/**
 * Copyright 2017, FMR LLC.
 * All Rights Reserved.
 * Fidelity Confidential Information
 */
package net.stn.streams.parallel;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

import lombok.extern.slf4j.Slf4j;

/**
 * @author a365143
 *
 */
@Slf4j
public class ForkJoinSumCalculator extends RecursiveTask<Long> {

	private final long[] numbers;
	private final int start;
	private final int end;

	public static final long THRESHOLD = 10_000;

	public ForkJoinSumCalculator(long[] numbers) {
		this(numbers, 0, numbers.length);
	}

	private ForkJoinSumCalculator(long[] numbers, int start, int end) {
		this.numbers = numbers;
		this.start = start;
		this.end = end;
	}

	/**
	 * 
	 * <p>
	 * {@inheritDoc}
	 */
	@Override
	protected Long compute() {
		log.debug("computing asynchronously...start:{}-end:{}", this.start, this.end);
		int length = end - start;
		if (length <= THRESHOLD) {
			return computeSequentially();
		}

		final ForkJoinSumCalculator leftTask = new ForkJoinSumCalculator(numbers, start, start + length / 2);
		// asynchronously execute the newly created subtask using another thread of the ForkJoinPool
		leftTask.fork();

		// create a subtask to sum the second half of the array
		final ForkJoinSumCalculator rightTask = new ForkJoinSumCalculator(numbers, start + length / 2, end);
		// Execute this second task sequentially, potentially allowing further recursive splits
		Long rightTaskResult = rightTask.compute();
		// read the result of the first task or wait for it isn't completed
		Long leftTaskResult = leftTask.join();

		return leftTaskResult + rightTaskResult;

	}

	private long computeSequentially() {
		log.info("computing sequentially...");
		long sum = 0;
		for (long number : numbers) {
			sum += number;
		}
		return sum;
	}

	public static void main(String[] args) {

		long[] numbers = LongStream.rangeClosed(1, 10_000).toArray();
		ForkJoinSumCalculator forkJoinSumCalculator = new ForkJoinSumCalculator(numbers);
		Long result = new ForkJoinPool().invoke(forkJoinSumCalculator);

		log.debug("result:{}", result);
	}
}
