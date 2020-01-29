// Name: 		Chong Chen
// USC NetID:	7460787319
// CS 455 PA3
// Fall 2019

/** 
   MineField
      class with locations of mines for a game.
      This class is mutable, because we sometimes need to change it once it's created.
      mutators: populateMineField, resetEmpty
      includes convenience method to tell the number of mines adjacent to a location.
 */
//import java.util.Arrays;
import java.util.Random;

public class MineField {

	//PRE: the 2D Array mineData has numRows rows and numCols columns.
	private int numRows;
	private int numCols;
	private boolean[][] mineData;
	
	//PRE: 0 <= numMines <= numRows * numCols
	private int numMines;

	public static final boolean HAS_MINE = true;
	public static final boolean NON_MINE = false;

	/**
	 * Create a minefield with same dimensions as the given array, and populate it
	 * with the mines in the array such that if mineData[row][col] is true, then
	 * hasMine(row,col) will be true and vice versa. numMines() for this minefield
	 * will corresponds to the number of 'true' values in mineData.
	 * 
	 * @param mineData the data for the mines; must have at least one row and one
	 *                 col.
	 */
	public MineField(boolean[][] mineData) {
		numRows = mineData.length;
		numCols = mineData[0].length;
		this.mineData = new boolean[numRows][numCols];
		
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numCols; j++) {
				if (mineData[i][j] == HAS_MINE) {
					this.mineData[i][j] = HAS_MINE;
					numMines++;
				}
				
				else {
					this.mineData[i][j] = NON_MINE;
				}
			}
		}

	}

	/**
	 * Create an empty minefield (i.e. no mines anywhere), that may later have
	 * numMines mines (once populateMineField is called on this object). Until
	 * populateMineField is called on such a MineField, numMines() will not
	 * correspond to the number of mines currently in the MineField.
	 * 
	 * @param numRows  number of rows this minefield will have, must be positive
	 * @param numCols  number of columns this minefield will have, must be positive
	 * @param numMines number of mines this minefield will have, once we populate
	 *                 it. PRE: numRows > 0 and numCols > 0 and 0 <= numMines < (1/3
	 *                 of total number of field locations).
	 */
	public MineField(int numRows, int numCols, int numMines) {
		this.numRows = numRows;
		this.numCols = numCols;
		this.numMines = numMines;

		mineData = new boolean[this.numRows][this.numCols];
		resetEmpty();
	}

	/**
	 * Removes any current mines on the minefield, and puts numMines() mines in
	 * random locations on the minefield, ensuring that no mine is placed at (row,
	 * col).
	 * 
	 * @param row the row of the location to avoid placing a mine
	 * @param col the column of the location to avoid placing a mine PRE:
	 *            inRange(row, col)
	 */
	public void populateMineField(int row, int col) {
		// remove current mines
		resetEmpty();

		// generate numMines randomly
		int mineCount = 0;
		int row_NextMine = 0;
		int col_NextMine = 0;
		Random generator = new Random();

		while (mineCount < numMines) {
			row_NextMine = generator.nextInt(numRows);
			col_NextMine = generator.nextInt(numCols);
			if (!(row_NextMine == row && col_NextMine == col) && (mineData[row_NextMine][col_NextMine] == NON_MINE)) {
				mineData[row_NextMine][col_NextMine] = HAS_MINE;
				mineCount++;
			}
		}
	}

	/**
	 * Reset the minefield to all empty squares. This does not affect numMines(),
	 * numRows() or numCols() Thus, after this call, the actual number of mines in
	 * the minefield does not match numMines(). Note: This is the state the
	 * minefield is in at the beginning of a game.
	 */
	public void resetEmpty() {
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numCols; j++) {
				mineData[i][j] = NON_MINE;
			}
		}
	}

	/**
	 * Returns the number of mines adjacent to the specified mine location (not
	 * counting a possible mine at (row, col) itself). Diagonals are also considered
	 * adjacent, so the return value will be in the range [0,8]
	 * 
	 * @param row row of the location to check
	 * @param col column of the location to check
	 * @return the number of mines adjacent to the square at (row, col) PRE:
	 *         inRange(row, col)
	 */
	public int numAdjacentMines(int row, int col) {
		int numAdjacentMines = 0;

		for (int i = row - 1; i <= row + 1; i++) {
			for (int j = col - 1; j <= col + 1; j++) {
				if (inRange(i, j) && !(i == row && j == col) && hasMine(i, j)) {
					numAdjacentMines++;
				}
			}
		}

		return numAdjacentMines;
	}

	/**
	 * Returns true iff (row,col) is a valid field location. Row numbers and column
	 * numbers start from 0.
	 * 
	 * @param row row of the location to consider
	 * @param col column of the location to consider
	 * @return whether (row, col) is a valid field location
	 */
	public boolean inRange(int row, int col) {
		if ((row >= 0 && row < numRows) && (col >= 0 && col < numCols)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Returns the number of rows in the field.
	 * 
	 * @return number of rows in the field
	 */
	public int numRows() {
		return numRows;
	}

	/**
	 * Returns the number of columns in the field.
	 * 
	 * @return number of columns in the field
	 */
	public int numCols() {
		return numCols;
	}

	/**
	 * Returns whether there is a mine in this square
	 * 
	 * @param row row of the location to check
	 * @param col column of the location to check
	 * @return whether there is a mine in this square PRE: inRange(row, col)
	 */
	public boolean hasMine(int row, int col) {
		return mineData[row][col];
	}

	/**
	 * Returns the number of mines you can have in this minefield. For mines created
	 * with the 3-arg constructor, some of the time this value does not match the
	 * actual number of mines currently on the field. See doc for that constructor,
	 * resetEmpty, and populateMineField for more details.
	 * 
	 * @return the number of mines
	 */
	public int numMines() {
		return numMines;
	}

}
