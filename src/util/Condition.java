package util;

/**
 * Checks the state of the world.
 * 
 * @author kerengu
 * 
 */
public abstract class Condition {

	public String name;
	public String[] args;
	
	public Condition(String name, String[] args){
		this.name = name;
		this.args = args;
	}
	
	public abstract boolean check(State s);
}
