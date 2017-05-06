/**
 * Copyright 2017, FMR LLC.
 * All Rights Reserved.
 * Fidelity Confidential Information
 */
package net.stn.streams.parallel;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.function.Function;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import lombok.extern.slf4j.Slf4j;

/**
 * @author A365143
 *
 */
@Slf4j
public class StreamParallelExercise {

	public static void main(String[] args) {
		// infiniteStream(1_000);
		// infiniteParallelStream(1_000);

		long timeTaken = measureSumPerf(ParallelStream::parallelSum, 10_000_000);
		log.debug("timeTaken#1:{} ms", timeTaken);
		timeTaken = measureSumPerf(ParallelStream::iterativeSum, 10_000_000);
		log.debug("timeTaken#2:{} ms", timeTaken);
		timeTaken = measureSumPerf(ParallelStream::rangedSum, 10_000_000);
		log.debug("timeTaken#3:{} ms", timeTaken);
		timeTaken = measureSumPerf(ParallelStream::parallelRangedSum, 10_000_000);
		log.debug("timeTaken#4:{} ms", timeTaken);
		measureSumPerf(StreamParallelExercise::forkJoinSum, 10_000_000);
		log.debug("timeTaken#5:{} ms", timeTaken);

	}

	public static void infiniteStream(final long limit) {
		Stream.iterate(1L, i -> i + 1).limit(limit).forEach(i -> log.debug("i={}", i));
	}

	public static void infiniteParallelStream(final long limit) {
		Stream.iterate(1L, i -> i + 1).limit(limit).parallel().forEach(i -> log.debug("i={}", i));
	}

	public static long measureSumPerf(Function<Long, Long> adder, long n) {
		long fastest = Long.MAX_VALUE;
		for (int i = 0; i < 10; i++) {
			long start = System.nanoTime();
			long sum = adder.apply(n);
			long duration = (System.nanoTime() - start) / 1_000_000;
			// log.debug("Result:{}", sum);
			if (duration < fastest)
				fastest = duration;
		}
		return fastest;
	}

	public static long forkJoinSum(long n) {
		long[] numbers = LongStream.rangeClosed(1, n).toArray();
		ForkJoinTask<Long> task = new ForkJoinSumCalculator(numbers);
		return new ForkJoinPool().invoke(task);
	}

	static class ParallelStream {
		public static long parallelSum(long number) {
			return Stream.iterate(1L, i -> i + 1).limit(number).parallel().reduce(0L, Long::sum);
		}

		public static long iterativeSum(long n) {
			long result = 0;
			for (long i = 1L; i <= n; i++) {
				result += i;
			}
			return result;
		}

		public static long rangedSum(long n) {
			return LongStream.rangeClosed(0L, n).reduce(1L, Long::sum);
		}

		public static long parallelRangedSum(long n) {
			return LongStream.rangeClosed(1, n)
					.parallel()
					.reduce(0L, Long::sum);
		}
	}
}
