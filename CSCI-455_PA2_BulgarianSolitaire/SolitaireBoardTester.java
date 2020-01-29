
/**
 * class SolitaireBoardTester
 *
 * This class test the SolitaireBoard class.
 *
 */
import java.util.ArrayList;

public class SolitaireBoardTester {
	public static void main(String[] args) {
		SolitaireBoard board1 = new SolitaireBoard();
		runSim(board1);
		int[] nums = {40, 1, 1, 1, 1, 1};
		ArrayList<Integer> initPiles = new ArrayList<>();
		for (int i = 0; i < nums.length; i++) {
			initPiles.add(i, nums[i]);
		}
		SolitaireBoard board2 = new SolitaireBoard(initPiles);
		runSim(board2);
	}

	private static void runSim(SolitaireBoard initBoard) {
		int roundCount = 0;
		System.out.print("[" + roundCount + "]");
		System.out.println(initBoard.configString());
		while (! initBoard.isDone()) {
			initBoard.playRound();
			roundCount++;
			System.out.print("[" + roundCount + "]");
			System.out.println(initBoard.configString());
		}
	}
}