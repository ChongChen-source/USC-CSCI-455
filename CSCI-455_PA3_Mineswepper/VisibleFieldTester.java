
public class VisibleFieldTester {

	public static void main(String[] args) {
		boolean[][] smallMineField = 
		      {{false, false, false, false}, 
		       {true, false, false, false}, 
		       {false, false, false, false},
		       {false, true, false, true}};
		MineField mf = new MineField(smallMineField);
		VisibleField vf = new VisibleField(mf);
	}

}
