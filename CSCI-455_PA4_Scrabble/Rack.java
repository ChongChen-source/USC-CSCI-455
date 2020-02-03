// Name: 		Chong Chen
// USC NetID: 	chongche
// CS 455 PA4
// Fall 2019

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;

/**
 * class Rack
 * 
 * This class stores the current rack of Scrabble tiles. This class corresponds to the idea
 * of the rack in the problem description. Thus, wherever your program is using
 * a rack, it should be using an object of type Rack. As previously discussed,
 * we have already provided the code for a private static Rack method
 * allSubsets.
 */

public class Rack {

	/**
	 * Returns all subsets of a String.
	 * 
	 * @param s the string to find all subsets, represents the current rack with
	 *          letters.
	 * @return all the subsets as String in an ArrayList.
	 */
	public static ArrayList<String> getAllSubsets(String s) {
		// unique, a string of unique letters shown in the given string s.
		String unique = new String();

		// mult, the Array represents the multiplicity of each letter from the string
		// unique.
		int[] mult = new int[letterMap(s).keySet().size()];

		// Read the information from the multiset representing the given string s.
		int loc = 0;
		for (Character key : letterMap(s).keySet()) {
			unique += key;
			mult[loc] = letterMap(s).get(key);
			loc++;
		}
		return allSubsets(unique, mult, 0);
	}

	/**
	 * Helper method to represent a String as a multiset with a HashMap. The HashMap
	 * contains unique letters(as Key) shown in the String and their numbers of
	 * repetitions(as Value).
	 * 
	 * @param s string to process.
	 * @return a Map to represent the String as a multiset.
	 */
	private static Map<Character, Integer> letterMap(String s) {
		char[] letters = s.toCharArray();
		Arrays.sort(letters);
		Map<Character, Integer> letterMap = new HashMap<>();

		for (char element : letters) {
			// Condition when one letter first shown in the string
			if (!letterMap.containsKey(element)) {
				letterMap.put(element, 1);
			}
			// Condition when one letter repeats in the string
			else {
				letterMap.replace(element, letterMap.get(element) + 1);
			}
		}

		return letterMap;
	}

	/**
	 * Finds all subsets of the multiset starting at position k in unique and mult.
	 * unique and mult describe a multiset such that mult[i] is the multiplicity of
	 * the char unique.charAt(i). PRE: mult.length must be at least as big as
	 * unique.length() 0 <= k <= unique.length()
	 * 
	 * @param unique a string of unique letters
	 * @param mult   the multiplicity of each letter from unique.
	 * @param k      the smallest index of unique and mult to consider.
	 * @return all subsets of the indicated multiset. Unlike the multiset in the
	 *         parameters, each subset is represented as a String that can have
	 *         repeated characters in it.
	 * @author Claire Bono
	 */
	private static ArrayList<String> allSubsets(String unique, int[] mult, int k) {
		ArrayList<String> allCombos = new ArrayList<>();

		if (k == unique.length()) { // multiset is empty
			allCombos.add("");
			return allCombos;
		}

		// get all subsets of the multiset without the first unique char
		ArrayList<String> restCombos = allSubsets(unique, mult, k + 1);

		// prepend all possible numbers of the first char (i.e., the one at position k)
		// to the front of each string in restCombos. Suppose that char is 'a'...

		String firstPart = ""; // in outer loop firstPart takes on the values: "", "a", "aa", ...
		for (int n = 0; n <= mult[k]; n++) {
			for (int i = 0; i < restCombos.size(); i++) { // for each of the subsets
															// we found in the recursive call
				// create and add a new string with n 'a's in front of that subset
				allCombos.add(firstPart + restCombos.get(i));
			}
			firstPart += unique.charAt(k); // append another instance of 'a' to the first part
		}

		return allCombos;
	}

}

