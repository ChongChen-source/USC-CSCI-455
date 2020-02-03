// Name: 		Chong Chen
// USC NetID: 	chongche
// CS 455 PA4
// Fall 2019

/**
 * class ScoreTable
 * 
 * This class has the information about how much each scrabble letter is worth.
 * This class work for both upper and lower case versions of the letter.
 * 
 */

public class ScoreTable {
	public static final int ALPHABET_SIZE = 26;
	public static final int ASCII_A = 65;
	public static final int ASCII_a = 97;

	/**
	 * Stores the socre for each letter. Letters that occur more often in the
	 * English language are worth less (e.g., 'e' and 's' are each worth 1 point),
	 * and letters that occur less often are worth more (e.g., 'q' and 'z' are worth
	 * 10 points each).
	 * 
	 * Here are all the letter values: (1 point)-A, E, I, O, U, L, N, S, T, R (2
	 * points)-D, G (3 points)-B, C, M, P (4 points)-F, H, V, W, Y (5 points)-K (8
	 * points)- J, X (10 points)-Q, Z
	 * 
	 * The score of a distinct letter is the same for both upper and lower case
	 * versions of the letter.
	 * 
	 * @return An ALPHABET_SIZE long array which contains the score information for
	 *         each letter.
	 * 
	 */
	private static int[] scores() {
		/**
		 * Representation invariant:
		 * 
		 * scores.length = ALPHABET_SIZE;
		 * 
		 * scores[0] ~ scores[ALPHABET_SIZE - 1] contains the score information for the
		 * first letter of English ('a' or 'A') to the last letter of English ('z' or 'Z')
		 * one-in-one;
		 * 
		 * The different locations of the scores array represents different English letters.
		 */
		int[] scores = new int[ALPHABET_SIZE];
		for (int i = ASCII_A; i < ASCII_A + ALPHABET_SIZE; i++) {
			if (i == 'D' || i == 'G') {
				scores[i - ASCII_A] = 2;
			} else if (i == 'B' || i == 'C' || i == 'M' || i == 'P') {
				scores[i - ASCII_A] = 3;
			} else if (i == 'F' || i == 'H' || i == 'V' || i == 'W' || i == 'Y') {
				scores[i - ASCII_A] = 4;
			} else if (i == 'K') {
				scores[i - ASCII_A] = 5;
			} else if (i == 'J' || i == 'X') {
				scores[i - ASCII_A] = 8;
			} else if (i == 'Q' || i == 'Z') {
				scores[i - ASCII_A] = 10;
			} else {
				scores[i - ASCII_A] = 1;
			}
		}
		return scores;
	}

	/**
	 * Rerturns the score of a scrabble word by computing the whole score of each
	 * letter in the word.
	 * 
	 * @param s		the scrabble word to be scored
	 * @return the score of the scrabble word
	 */
	public static int getScore(String s) {
		int score = 0;
		char[] letters = s.toCharArray();
		for (char element : letters) {

			// Condition that the element is a lower case letter
			if (element - ASCII_a >= 0) {
				score += scores()[element - ASCII_a];
			}
			// Condition that the element is a upper case letter
			else {
				score += scores()[element - ASCII_A];
			}
		}
		return score;
	}
}