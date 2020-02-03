// Name: 		Chong Chen
// USC NetID: 	chongche
// CS 455 PA4
// Fall 2019

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Collections;
import java.util.Comparator;

public class WordFinder {
	public static void main(String[] args) throws FileNotFoundException {
		try {
			boolean optionalConfig = (args.length > 0);

			final String DEFAULT_DICTIONARY_NAME = "sowpods.txt";
			String dictionaryName = new String();

			if (optionalConfig) {
				dictionaryName = args[0];
			} else {
				dictionaryName = DEFAULT_DICTIONARY_NAME;
			}

			AnagramDictionary anagramDictionary = new AnagramDictionary(dictionaryName);
			System.out.println("Type . to quit.");

			Scanner in = new Scanner(System.in);
			System.out.print("Rack? ");

			while (in.hasNext()) {
				// String s represents the current rack.
				String currentRack = in.next();

				// quit when detect "."
				if (currentRack.equals(".")) {
					return;
				}

				// Process the current rack string s with the given dictionary
				Map<String, Integer> scoreMap = getScoreMap(anagramDictionary, currentRack);

				// Sort the valid words with their score, in decreasing
				// order by score. For words with the same scrabble score, the words must appear
				// in alphabetical order.
				ArrayList<Map.Entry<String, Integer>> sortedScoreMapEntries = getSortedScoreMapEntries(scoreMap);

				// Print out all the valid words with their scores as sorted
				printResult(sortedScoreMapEntries, currentRack);

				// Ask for next rack to process
				System.out.print("Rack? ");
			}
		} catch (FileNotFoundException e) {
			System.out.println("The dictionary file does not exist.");
		}
	}

	/**
	 * Generate a Map contains all the valid words possible form the current rack
	 * string(as Key), and the score of each valid word(as Value)
	 * 
	 * @param anagramDictionary the processed dictionary, sorted with different
	 *                          canonical forms, with list of all its anagrams.
	 * @param currentRack       the string to process, represents the current rack.
	 * @return a Map contains all the valid words possible form the current rack
	 *         string(as Key), and the score of each valid word(as Value)
	 */
	private static Map<String, Integer> getScoreMap(AnagramDictionary anagramDictionary, String currentRack) {
		ArrayList<String> subsets = Rack.getAllSubsets(currentRack);
		Map<String, Integer> scoreMap = new HashMap<>();

		for (String element : subsets) {
			if (!element.equals(null) && !element.equals("")) {
				ArrayList<String> anagramsValid = anagramDictionary.getAnagramsOf(element);
				if (anagramsValid != null) {
					for (String anagrams : anagramsValid) {
						scoreMap.put(anagrams, ScoreTable.getScore(anagrams));
					}
				}
			}
		}
		return scoreMap;
	}

	/**
	 * Generates a list of sorted valid words paired with their score, in decreasing
	 * order by score. For words with the same scrabble score, the words must appear
	 * in alphabetical order.
	 * 
	 * @param scoreMap the Map contains all the valid words possible form the
	 *                 current rack string(as Key), and the score of each valid
	 *                 word(as Value)
	 * @return a list of sorted valid words paired with their score
	 */
	private static ArrayList<Map.Entry<String, Integer>> getSortedScoreMapEntries(Map<String, Integer> scoreMap) {
		// Implement the comparator for the entries of scoreMap
		Comparator<Map.Entry<String, Integer>> comparator = new Comparator<Map.Entry<String, Integer>>() {
			   @Override
           public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
				// For words with the same score, sort the entries with the words in
				// alphabetical order.
				if (o1.getValue() == o2.getValue()) {
					return o1.getKey().compareTo(o2.getKey());
				}
				// Sort the words in decreasing order by score.
				return o2.getValue() - o1.getValue();
			}
		};

		// Generate the list contains the information of the scoreMap
		ArrayList<Map.Entry<String, Integer>> scoreMapEntries = new ArrayList<>();
		for (Map.Entry<String, Integer> entry : scoreMap.entrySet()) {
			scoreMapEntries.add(entry);
		}

		// Sort the list with above comparator
		Collections.sort(scoreMapEntries, comparator);

		return scoreMapEntries;
	}

	/**
	 * Print out all the valid words with their scores as sorted.
	 * 
	 * @param sortedScoreMapEntries a list of sorted valid words paired with their
	 *                              score, in decreasing order by score. For words
	 *                              with the same scrabble score, the words must
	 *                              appear in alphabetical order.
	 * @param currentRack           the string to process, represents the current
	 *                              rack.
	 */
	private static void printResult(ArrayList<Map.Entry<String, Integer>> sortedScoreMapEntries, String currentRack) {
		System.out.println("We can make " + sortedScoreMapEntries.size() + " words from \"" + currentRack + "\"");
		if (sortedScoreMapEntries.size() > 0) {
			System.out.println("All of the words with their scores (sorted by score): ");
			for (Map.Entry<String, Integer> entry : sortedScoreMapEntries) {
				System.out.println(entry.getValue() + ": " + entry.getKey());
			}
		}
	}
}

