package blocksworld;

import java.util.ArrayList;
import java.util.List;

import util.State;

/**
 * Models a single vertical column in blocks world.
 * 
 * @author kerengu
 * 
 */
public class BWState extends State {

	private List<String> column;

	private static int MAX_COL_HEIGHT = 5;

	public BWState() {
		super();
		column = new ArrayList<String>(MAX_COL_HEIGHT);
	}

	public List<String> getColumn() {
		return column;
	}

}
