package net.stn.streams;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author SiddhuNalwad <br>
 * 
 *         <pre>
 *         EXCERCISE: 
 *         1. Find the distinct words in a given file using streams.
 *         2. Find distinct words and their occurrences in a given file using streams.
 *         </pre>
 */
@Slf4j
public class FilesTest {

	public static void main(final String[] args) throws IOException {

		log.debug("Working directory:{}", System.getProperty("user.dir"));

		// IST METHOD
		final List<String> distinctWords = Files
				.lines(Paths.get(System.getProperty("user.dir") + "/src/main/java/net/stn/streams/FilesTest.java"))
				.map(line -> line.split("\\s+")).flatMap(Arrays::stream).distinct().collect(Collectors.toList());

		// distinctWords.forEach(System.out::println);
		log.debug("Number of Words using Ist method:{}", distinctWords.size());

		log.debug("======================================");

		// IIND METHOD
		final Set<String> words = Files
				.lines(Paths.get(System.getProperty("user.dir") + "/src/main/java/net/stn/streams/FilesTest.java"))
				.map(line -> line.split("\\s+")).flatMap(Arrays::stream).collect(Collectors.toCollection(HashSet::new));

		log.debug("Number of Words using IInd method:{}", words.size());
		// words.stream().forEach(System.out::println);

		// To find distinct words and their occurrence
		final Map<String, Long> wordsCountMap = Files
				.lines(Paths.get(System.getProperty("user.dir") + "/src/main/java/net/stn/streams/FilesTest.java"))
				.map(line -> line.split("\\s+")).flatMap(Arrays::stream)
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

		log.debug("Words with occurrence:{}", wordsCountMap);
	}
}
