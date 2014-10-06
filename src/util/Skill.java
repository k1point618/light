package util;

import java.util.ArrayList;
import java.util.List;

/**
 * Skill encodes methods that can be executed and change the environment.
 * 
 * @author kerengu
 * 
 */
public abstract class Skill {

	protected String name;
	protected ArrayList<String> args;

	// Defines the single condition at which the skill can be executed.
	protected Goal precondition;

	// Defines the set of effects of the skill
	protected List<Goal> effects;

	// List of sub skills that needs to be executed in order.
	protected List<Skill> subSkills;

	public Skill(){
		
	}
	
	public Skill(String name, ArrayList<String> args) {
		this.name = name;
		this.args = args;
	}

	public abstract void execute(State state);

	public abstract void checkPrecondition(State state);
	
	public List<Goal> getEffects(){
		return effects;
	}
	
	public Goal getPrecondition(){
		return precondition;
	}

}
