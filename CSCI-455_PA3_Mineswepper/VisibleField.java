// Name: 		Chong Chen
// USC NetID:	7460787319
// CS 455 PA3
// Fall 2019

/**
 * VisibleField class This is the data that's being displayed at any one point
 * in the game (i.e., visible field, because it's what the user can see about
 * the minefield), Client can call getStatus(row, col) for any square. It
 * actually has data about the whole current state of the game, including the
 * underlying mine field (getMineField()). Other accessors related to game
 * status: numMinesLeft(), isGameOver(). It also has mutators related to actions
 * the player could do (resetGameDisplay(), cycleGuess(), uncover()), and
 * changes the game state accordingly.
 * 
 * It, along with the MineField (accessible in mineField instance variable),
 * forms the Model for the game application, whereas GameBoardPanel is the View
 * and Controller, in the MVC design pattern. It contains the MineField that
 * it's partially displaying. That MineField can be accessed (or modified) from
 * outside this class via the getMineField accessor.
 */
public class VisibleField {
	// ----------------------------------------------------------
	// The following public constants (plus numbers mentioned in comments below) are
	// the possible states of one
	// location (a "square") in the visible field (all are values that can be
	// returned by public method
	// getStatus(row, col)).

	// Covered states (all negative values):
	public static final int COVERED = -1; // initial value of all squares
	public static final int MINE_GUESS = -2;
	public static final int QUESTION = -3;

	// Uncovered states (all non-negative values):

	// values in the range [0,8] corresponds to number of mines adjacent to this
	// square

	public static final int MINE = 9; // this loc is a mine that hasn't been guessed already (end of losing game)
	public static final int INCORRECT_GUESS = 10; // is displayed a specific way at the end of losing game
	public static final int EXPLODED_MINE = 11; // the one you uncovered by mistake (that caused you to lose)
	// ----------------------------------------------------------

	// PRE: the mineField and the square have the same size(same rows and same
	// columns).
	private MineField mineField;
	private int[][] squares; // the table to cover mineField

	// PRE: 0 <= numMines, numMinesGuessed <= mineField.numRows() *
	// mineField.numCols().
	private int numMines;
	private int numMinesGuessed;

	/**
	 * Create a visible field that has the given underlying mineField. The initial
	 * state will have all the mines covered up, no mines guessed, and the game not
	 * over.
	 * 
	 * @param mineField the minefield to use for for this VisibleField
	 */
	public VisibleField(MineField mineField) {
		this.mineField = mineField;
		numMines = this.mineField.numMines();
		squares = new int[this.mineField.numRows()][this.mineField.numCols()];
		resetGameDisplay();
	}

	/**
	 * Reset the object to its initial state (see constructor comments), using the
	 * same underlying MineField.
	 */
	public void resetGameDisplay() {
		for (int i = 0; i < this.mineField.numRows(); i++) {
			for (int j = 0; j < this.mineField.numCols(); j++) {
				squares[i][j] = COVERED;
			}
		}
		numMinesGuessed = 0;
	}

	/**
	 * Returns a reference to the mineField that this VisibleField "covers"
	 * 
	 * @return the minefield
	 */
	public MineField getMineField() {
		return mineField;
	}

	/**
	 * Returns the visible status of the square indicated.
	 * 
	 * @param row row of the square
	 * @param col col of the square
	 * @return the status of the square at location (row, col). See the public
	 *         constants at the beginning of the class for the possible values that
	 *         may be returned, and their meanings. PRE: getMineField().inRange(row,
	 *         col)
	 */
	public int getStatus(int row, int col) {
		return squares[row][col];
	}

	/**
	 * Returns the the number of mines left to guess. This has nothing to do with
	 * whether the mines guessed are correct or not. Just gives the user an
	 * indication of how many more mines the user might want to guess. This value
	 * can be negative, if they have guessed more than the number of mines in the
	 * minefield.
	 * 
	 * @return the number of mines left to guess.
	 */
	public int numMinesLeft() {
		return numMines - numMinesGuessed;

	}

	/**
	 * Cycles through covered states for a square, updating number of guesses as
	 * necessary. Call on a COVERED square changes its status to MINE_GUESS; call on
	 * a MINE_GUESS square changes it to QUESTION; call on a QUESTION square changes
	 * it to COVERED again; call on an uncovered square has no effect.
	 * 
	 * @param row row of the square
	 * @param col col of the square PRE: getMineField().inRange(row, col)
	 */
	public void cycleGuess(int row, int col) {
		if (getStatus(row, col) == COVERED) {
			squares[row][col] = MINE_GUESS;
			numMinesGuessed++;
		}

		else if (getStatus(row, col) == MINE_GUESS) {
			squares[row][col] = QUESTION;
			numMinesGuessed--;
		}

		else if (getStatus(row, col) == QUESTION) {
			squares[row][col] = COVERED;
		}
	}

	/**
	 * Uncovers this square and returns false iff you uncover a mine here. If the
	 * square wasn't a mine or adjacent to a mine it also uncovers all the squares
	 * in the neighboring area that are also not next to any mines, possibly
	 * uncovering a large region. Any mine-adjacent squares you reach will also be
	 * uncovered, and form (possibly along with parts of the edge of the whole
	 * field) the boundary of this region. Does not uncover, or keep searching
	 * through, squares that have the status MINE_GUESS. Note: this action may cause
	 * the game to end: either in a win (opened all the non-mine squares) or a loss
	 * (opened a mine).
	 * 
	 * @param row of the square
	 * @param col of the square
	 * @return false iff you uncover a mine at (row, col) PRE:
	 *         getMineField().inRange(row, col)
	 */
	public boolean uncover(int row, int col) {
		if (canBeUncovered(row, col)) {

			// uncover covered a has-mine square condition
			if (!isSafeToUncover(row, col)) {
				squares[row][col] = EXPLODED_MINE;

				// change squares' state for loss condition
				for (int i = 0; i < mineField.numRows(); i++) {
					for (int j = 0; j < mineField.numCols(); j++) {
						if (mineField.hasMine(i, j) && (getStatus(i, j) == COVERED || getStatus(i, j) == QUESTION)) {
							squares[i][j] = MINE;
						}

						else if (!mineField.hasMine(i, j) && getStatus(i, j) == MINE_GUESS) {
							squares[i][j] = INCORRECT_GUESS;
						}

						else if (!mineField.hasMine(i, j) && getStatus(i, j) == QUESTION) {
							squares[i][j] = COVERED;
						}
					}
				}

				return false;
			}

			// uncover a covered non-mine square condition
			else {
				openSquaresRecursively(row, col);
				return true;
			}
		}

		// Does not uncover uncovered or guessed squares
		else {
			return true;
		}
	}

	/**
	 * Returns whether the game is over. (Note: This is not a mutator.)
	 * 
	 * @return whether game over
	 */
	public boolean isGameOver() {
		// loss condition
		for (int i = 0; i < mineField.numRows(); i++) {
			for (int j = 0; j < mineField.numCols(); j++) {
				if (getStatus(i, j) == EXPLODED_MINE) {
					return true;
				}
			}
		}

		// win condition
		for (int i = 0; i < mineField.numRows(); i++) {
			for (int j = 0; j < mineField.numCols(); j++) {
				if (!mineField.hasMine(i, j) && !isUncovered(i, j)) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Returns whether this square has been uncovered. (i.e., is in any one of the
	 * uncovered states, vs. any one of the covered states).
	 * 
	 * @param row of the square
	 * @param col of the square
	 * @return whether the square is uncovered PRE: getMineField().inRange(row, col)
	 */
	public boolean isUncovered(int row, int col) {
		return (squares[row][col] >= 0) ? true : false;
	}

	/**
	 * If the start square has no adjacent mines, this private mutator recursively
	 * uncovers all the squares in that region that aren't adjacent to mines until
	 * it gets to the boundary of the field, or squares adjacent to other mines.
	 * 
	 * @param row of the square
	 * @param col of the square PRE: isSafeToUncover(row, col)
	 */
	private void openSquaresRecursively(int row, int col) {
		squares[row][col] = mineField.numAdjacentMines(row, col);

		// terminate condition for this recursion
		if (isRecursionBoundary(row, col)) {
			return;
		}

		for (int i = row - 1; i <= row + 1; i++) {
			for (int j = col - 1; j <= col + 1; j++) {
				if (mineField.inRange(i, j) && canBeUncovered(i, j) && isSafeToUncover(i, j)) {
					openSquaresRecursively(i, j);
				}
			}
		}
	}

	/**
	 * Returns whether the square is safe to uncover (has no mine under it)
	 * 
	 * @param row of the square
	 * @param col of the square PRE: canBeUncovered(row, col)
	 * @return whether the square is safe to uncover
	 */
	private boolean isSafeToUncover(int row, int col) {
		return (!mineField.hasMine(row, col)) ? true : false;
	}

	/**
	 * Returns whether the square is able to be uncovered (is covered or not
	 * guessed)
	 * 
	 * @param row of the square
	 * @param col of the square PRE: mineField.inRange(row, col)
	 * @return whether the square is able to be uncovered
	 */
	private boolean canBeUncovered(int row, int col) {
		return (isUncovered(row, col) || getStatus(row, col) == MINE_GUESS) ? false : true;
	}

	/**
	 * Returns whether the square is the boundary of the recursion (whether the
	 * square is adjacent to any mines)
	 * 
	 * @param row of the square
	 * @param col of the square PRE: isSafeToUncover(row, col)
	 */
	private boolean isRecursionBoundary(int row, int col) {
		return (mineField.numAdjacentMines(row, col) >= 1) ? true : false;
	}
}
