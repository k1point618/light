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

	private List<Concept> positives;

	public State() {
		positives = new ArrayList<Concept>();
	}

	public boolean addConcept(Concept newC) {
		return positives.add(newC);
	}

	public boolean removeConcept(Concept oldC) {
		return positives.remove(oldC);
	}
}
