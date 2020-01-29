public class MineFieldTester {

	public static void main(String[] args) {
		MineField mineField = new MineField(3, 3, 4);
		mineField.populateMineField(1,1);
		//mineField.printField();
		System.out.println(mineField.numAdjacentMines(1,1));
		System.out.println(mineField.numAdjacentMines(0,0));
		System.out.println(mineField.numAdjacentMines(2,2));
	}
}