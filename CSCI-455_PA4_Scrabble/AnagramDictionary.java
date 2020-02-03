// Name: 		Chong Chen
// USC NetID: 	chongche
// CS 455 PA4
// Fall 2019

import java.io.FileNotFoundException;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;

/**
 * class AnagramDictionary
 * 
 * A dictionary of all anagram sets. Note: the processing is case-sensitive; so
 * if the dictionary has all lower case words, you will likely want any string
 * you test to have all lower case letters too, and likewise if the dictionary
 * words are all upper case.
 */

public class AnagramDictionary {

	/**
	 * Representation variable:
	 * 
	 * anagramMap contains the whole information in the dictionary given. It sorts
	 * the valid-words into different map entries, using each distinct canonical
	 * form as the Key, and a list contains all its anagrams as the Value.
	 */
	private Map<String, ArrayList<String>> anagramMap;

	/**
	 * Create an anagram dictionary from the list of words given in the file
	 * indicated by fileName. PRE: The strings in the file are unique.
	 * 
	 * @param fileName the name of the file to read from
	 * @throws FileNotFoundException if the file is not found
	 */
	public AnagramDictionary(String fileName) throws FileNotFoundException {
		anagramMap = new HashMap<>();

		File file = new File(fileName);
		Scanner in = new Scanner(file);
		while (in.hasNext()) {
			String word = in.next();
			String canonicalWord = canonicalForm(word);

			// Sort the dictionary into different sets, using each distinct canonical form string as
			// Key and the list of all its anagrams as value.
			if (anagramMap.containsKey(canonicalWord)) {
				anagramMap.get(canonicalWord).add(word);
			}

			else {
				ArrayList<String> anagrams = new ArrayList<>();
				anagrams.add(word);
				anagramMap.put(canonicalWord, anagrams);
			}
		}
	}

	/**
	 * Get all anagrams of the given string. This method is case-sensitive. E.g.
	 * "CARE" and "race" would not be recognized as anagrams.
	 * 
	 * @param s string to process
	 * @return a list of the anagrams of s
	 */
	public ArrayList<String> getAnagramsOf(String s) {
		return anagramMap.get(canonicalForm(s));
	}

	/**
	 * Helper method to find the canonical form of a given string.
	 * 
	 * @param word string to process
	 * @return the canonical form string
	 */
	private String canonicalForm(String word) {
		char[] letters = word.toCharArray();
		Arrays.sort(letters);
		String canonicalWord = new String();
		for (char element : letters) {
			canonicalWord += element;
		}
		return canonicalWord;
	}
}

