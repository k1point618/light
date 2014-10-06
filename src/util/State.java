package util;

import java.util.ArrayList;
import java.util.List;

/**
 * Defines the state of the world.
 * 
 * @author kerengu
 * 
 */
public abstract class State {

	private List<Goal> positives;

	public State() {
		positives = new ArrayList<Goal>();
	}

	public boolean addConcept(Goal newC) {
		return positives.add(newC);
	}

	public boolean removeConcept(Goal oldC) {
		return positives.remove(oldC);
	}
	
	public List<Goal> getConcepts(){
	    return positives;
	}
}
