// Name:		Chong Chen
// USC NetID:	7460787319
// CSCI455 PA2
// Fall 2019

import java.util.ArrayList;
import java.util.Random;

/*
  class SolitaireBoard
  The board for Bulgarian Solitaire.  You can change what the total 
  number of cards is for the game by changing NUM_FINAL_PILES, below.  
  Don't change CARD_TOTAL directly, because there are only some values
  for CARD_TOTAL that result in a game that terminates.  (See comments 
  below next to named constant declarations for more details on this.)
*/

public class SolitaireBoard {

	public static final int NUM_FINAL_PILES = 9;
	// number of piles in a final configuration
	// (note: if NUM_FINAL_PILES is 9, then CARD_TOTAL below will be 45)

	public static final int CARD_TOTAL = NUM_FINAL_PILES * (NUM_FINAL_PILES + 1) / 2;
	// bulgarian solitaire only terminates if CARD_TOTAL is a triangular number.
	// see: http://en.wikipedia.org/wiki/Bulgarian_solitaire for more details
	// the above formula is the closed form for 1 + 2 + 3 + . . . + NUM_FINAL_PILES

	// Note to students: you may not use an ArrayList -- see assgt
	// description for details.

	/**
	 * Representation invariant: 
	 * 0 <= currentSize <= CARD_TOTAL-1; 
	 * If currentSize > 0, locations of filled elements: [0, currentSize-1] 
	 * For 0 <= i <= currentSize - 1, 1 <= piles[i] <= CARD_TOTAL; 
	 * For 0 <= j <= CARD_TOTAL - 1, piles[j] = 0;
	 * The total value of piles[0] to piles[currentSize] = CARD_TOTAL;
	 */
	private int[] piles;
	private int currentSize;

	/**
	 * Creates a solitaire board with the configuration specified in piles. piles
	 * has the number of cards in the first pile, then the number of cards in the
	 * second pile, etc. PRE: piles contains a sequence of positive numbers that sum
	 * to SolitaireBoard.CARD_TOTAL
	 */
	public SolitaireBoard(ArrayList<Integer> piles) {
		// The number of piles have a upper bound (CARD_TOTAL + 1) instead ofCARD_TOTAL,
		// for the boundary case when the initial board is of CARD_TOTAL piles with one
		// card each.
		// In that case, we need an extra location to execute a round.
		this.piles = new int[CARD_TOTAL + 1];
		currentSize = piles.size();
		for (int i = 0; i < currentSize; i++) {
			this.piles[i] = piles.get(i);
		}
		// sample assert statement (you will be adding more of these calls)
		// this statement stays at the end of the constructor.
		assert isValidSolitaireBoard();
	}

	/**
	 * Creates a solitaire board with a random initial configuration.
	 */
	public SolitaireBoard() {
		Random generator = new Random();
		piles = new int[CARD_TOTAL + 1];
		currentSize = 0;
		int undealedCards = CARD_TOTAL;
		for (int i = 0; undealedCards > 0; i++) {
			piles[i] = generator.nextInt(undealedCards) + 1;
			currentSize++;
			undealedCards -= piles[i];
		}
		assert isValidSolitaireBoard();
	}

	/**
	 * Plays one round of Bulgarian solitaire. Updates the configuration according
	 * to the rules of Bulgarian solitaire: Takes one card from each pile, and puts
	 * them all together in a new pile. The old piles that are left will be in the
	 * same relative order as before, and the new pile will be at the end.
	 */
	public void playRound() {
		// takes one card from each pile
		for (int i = 0; i < currentSize; i++) {
			piles[i]--;
		}
		
		// put the cards taken all together in a new pile
		piles[currentSize] = currentSize;
		currentSize++;

		// remove empty piles
		for (int i = 0; i < currentSize; i++) {
			while (piles[i] == 0) {
				for (int j = i + 1; j < currentSize; j++) {
					piles[j - 1] = piles[j];
				}
				currentSize--;
			}
		}
		assert isValidSolitaireBoard();
	}

	/**
	 * Returns true iff the current board is at the end of the game. That is, there
	 * are NUM_FINAL_PILES piles that are of sizes 1, 2, 3, . . . , NUM_FINAL_PILES,
	 * in any order.
	 */

	public boolean isDone() {
		boolean result = false;
		
		// Each location of the Array finalCount represents for one possible 
		// number of cards in one pile on the final board.
		int[] finalCount = new int[NUM_FINAL_PILES];

		// The number of piles in final board must be NUM_FINAL_PILES;
		if (currentSize == NUM_FINAL_PILES) {
			for (int i = 0; i < currentSize; i++) {
				if ((piles[i] <= NUM_FINAL_PILES) && (piles[i] >= 1)) {
					finalCount[piles[i] - 1]++;
				} else {
					result = false;
				}
			}
			
			// Each pile contains distinct number of cards with 1, 2, ... , NUM_FINAL_PILES card(s), in any order.		
			// So each element of the Array finalCount must equals to 1, stands for "distinct".
			int count = 0;
			for (int i = 0; i < finalCount.length; i++) {
				if (finalCount[i] == 1) {
					count++;
				}
			}
			if (count == NUM_FINAL_PILES) {
				result = true;
			} 
			else {
				result = false;
			}
		}

		else {
			result = false;
		}
		assert isValidSolitaireBoard();
		return result;
	}

	/**
	 * Returns current board configuration as a string with the format of a
	 * space-separated list of numbers with no leading or trailing spaces. The
	 * numbers represent the number of cards in each non-empty pile.
	 */
	public String configString() {
		String config = Integer.toString(piles[0]);
		for (int i = 1; i < currentSize; i++) {
			config = config + " " + piles[i];
		}
		assert isValidSolitaireBoard();
		return config;
	}

	/**
	 * Returns true iff the solitaire board data is in a valid state (See
	 * representation invariant comment for more details.)
	 */
	private boolean isValidSolitaireBoard() {

		int sumCards = 0;

		// For 0 <= i <= currentSize - 1, 1 <= piles[i] <= CARD_TOTAL;
		for (int i = 0; i < currentSize; i++) {
			if (piles[i] <= CARD_TOTAL && piles[i] >= 1) {
				sumCards += piles[i];
			} 
		else {
			return false;
			}
		}

		// The total value of piles[0] to piles[currentSize] = CARD_TOTAL;
		if (sumCards == CARD_TOTAL) {
			return true;
		} 
		else {
			return false;
		}
	}

}
