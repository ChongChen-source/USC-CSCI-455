// Name: 		Chong Chen
// USC NetID:	7460787319
// CSCI455 PA2
// Fall 2019

import java.util.ArrayList;
import java.util.Scanner;

/**
 * class BulgarianSolitaireSimulator 
 * 
 * The simulator of the game BulgarianSolitaires, 
 * print out the boards after each round played until done.
 * Able to be run in a few different modes, 
 * controlled by two command-line arguments "-u" and "-s". 
 * 
 * "-u", short for user, prompts for the initial configuration 
 * from the user, instead of generating a random configuration. 
 * 
 * "-s", short for stop, stops between every round of the game.
 * The game only continues when the user hits enter (a.k.a., return).
 */

public class BulgarianSolitaireSimulator {
	/**
	 * the main method.
	 * detect the "-u" "-s" arguments.
	 * print out the boards after each round played until done.
	 */
	public static void main(String[] args) {

		boolean singleStep = false;
		boolean userConfig = false;

		for (int i = 0; i < args.length; i++) {
			if (args[i].equals("-u")) {
				userConfig = true;
			} 
			
			else if (args[i].equals("-s")) {
				singleStep = true;
			}
		}

		SolitaireBoard board;

		Scanner in = new Scanner(System.in);

		// Decide input mode with configuration "-u".
		if (userConfig) {

			// Print instructions for users.
			System.out.println("Number of total cards is " + SolitaireBoard.CARD_TOTAL);
			System.out.println(
					"You will be entering the initial configuration of the cards (i.e., how many in each pile). ");
			System.out.println("Please enter a space-separated list of positive integers followed by newline:");

			String line = in.nextLine();
			line = ValidateInput(line, in);
			board = new SolitaireBoard(getNums(line));
		} 
		
		else {
			board = new SolitaireBoard();
		}

		// Decide output mode with configuration "-s".
		if (singleStep) {
			runSingleStep(board, in);
		} 
		
		else {
			runContinuously(board);
		}
	}

	
	/**
	 * Read in the valid sequence of numbers, and convert is to an ArrayList.
	 * 
	 * @param line  the string stored the information of valid initial configuration.
	 */
	private static ArrayList<Integer> getNums(String line) {

		ArrayList<Integer> nums = new ArrayList<Integer>();
		Scanner lineScanner = new Scanner(line);

		while (lineScanner.hasNextInt()) {
			nums.add(lineScanner.nextInt());
		}

		return nums;
	}

	
	/**
	 * play rounds and print out the current configuration continuously.
	 * if no "-s" argument detected.
	 * 
	 * @param initBoard  the initial board of the game Bulgarian Solitaire
	 */
	private static void runContinuously(SolitaireBoard initBoard) {
		int roundCount = 0;
		System.out.println("Initial configuration: " + initBoard.configString());
		while (!initBoard.isDone()) {
			roundCount++;
			playRoundAndPrintConfig(initBoard, roundCount);
		}
		System.out.println("Done!");
	}

	/**
	 * play rounds and print out the current configuration step by step.
	 * if "-s" argument detected.
	 * 
	 * @param initBoard  the initial board of the game Bulgarian Solitaire
	 * @param sc  the scanner to detect "enter" for executing the next round. 
	 */
	private static void runSingleStep(SolitaireBoard initBoard, Scanner sc) {
		
		int roundCount = 0;
		System.out.println("Initial configuration: " + initBoard.configString());
		
		if (initBoard.isDone()) {
			System.out.println("Done!");
		} 
		
		else {
			roundCount++;
			playRoundAndPrintConfig(initBoard, roundCount);
			System.out.print("<Type return to continue>");

			while (!initBoard.isDone()) {
				// Detect "enter" for executing the next round.
				if (sc.nextLine().length() == 0) {
					roundCount++;
					playRoundAndPrintConfig(initBoard, roundCount);
					System.out.print("<Type return to continue>");
				}
			}
			System.out.println("\nDone!");
		}
	}

	
	/**
	 * validate the String typed-in by users, by printing out 
	 * ERROR alert and input instruction if invalid input occurred.
	 * 
	 * @param line  the String typed-in by users. (maybe invalid)
	 * @param sc  the scanner to read in new String if invalid input occurred. 
	 */
	private static String ValidateInput(String line, Scanner in) {
		while (!isValidLine(line)) {
			System.out.println("ERROR: Each pile must have at least one card and the total number of cards must be "
					+ SolitaireBoard.CARD_TOTAL);
			System.out.println("Please enter a space-separated list of positive integers followed by newline: ");
			line = in.nextLine();
		}
		return line;
	}

	
	/**
	 * judge the validness of  the String typed-in by users.
	 * 
	 * @param line  the String typed-in by users. (maybe invalid)
	 */
	private static boolean isValidLine(String line) {
		Scanner lineScanner = new Scanner(line);
		int sum = 0;
		
		// Each pile must have at least one card;
		while (lineScanner.hasNextInt()) {
			int value = lineScanner.nextInt();
			if ((value >= 1) && (value <= SolitaireBoard.CARD_TOTAL)) {
				sum += value;
			} 
			else {
				return false;
			}
		}

		// The total number of cards must be CARD_TOTAL.
		// The input must be integers.
		if (sum != SolitaireBoard.CARD_TOTAL || (lineScanner.hasNext() && !lineScanner.hasNextInt())) {
			return false;
		} 
		else {
			return true;
		}
	}

	
	/**
	 * play a round to the current board, 
	 * and print out the configuration with rounds played.
	 * 
	 * @param currentBoard  the current board of the game
	 * @param currentRounds  the rounds already taken form initial board to get arrive the current one.
	 */
	private static void playRoundAndPrintConfig(SolitaireBoard currentBoard, int currentRounds) {
		currentBoard.playRound();
		System.out.println("[" + currentRounds + "] Current configuration: " + currentBoard.configString());
	}
}
