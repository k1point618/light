package util;

import java.util.ArrayList;
import java.util.List;

/**
 * Goal has a name, a set of arguments, 
 * @author kerengu
 *
 */
public abstract class Goal {

	private String name;
	private String[] args;
	// The set of conditions that needs to be satisfied in order to achieve the
	// goal.
	private List<Condition> conditions;
	
	public Goal(String name, String[] args){
		this.name = name;
		this.args = args;
		conditions = new ArrayList<Condition>();
	}
	
	// Checks whether the state satisfies the set of conditions
	public boolean achieved(State state){
		for (Condition cond: conditions) {
			if (!cond.check(state)){
				return false;
			}
		}
		return true;
	}
	
	public void addConditions(Condition cond){
		conditions.add(cond);
	}
}
