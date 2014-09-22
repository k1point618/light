package blocksworld;

import java.util.ArrayList;
import java.util.Arrays;

import util.Condition;
import util.Skill;
import util.State;

/**
 * Defines the set of skills associated in the Blocks-World Application
 * 
 * @author kerengu
 * 
 */
public class BWSkills {

	/**
	 * Defines Skill: Stack
	 * @author kerengu
	 *
	 */
	public static class Stack extends Skill {

		public Stack() {
			
		}
		
		public Stack(String[] args) {
			super("Stack", args);

			// Specify Precondition
			this.precondition = new BWConcepts.Stackable(args);

			// Specify effects
			this.effects = new ArrayList<Condition>();
			this.effects.add(new BWCondition("On", args));
			this.effects.add(new BWCondition("HandEmpty", null));
		}

		@Override
		public void execute(State state) {
			BWState s = (BWState) state;
			int index = s.getColumn().indexOf(args[0]) + 1;
			if (s.getColumn().get(index) != null) {
				// Shouldn't reach here because we must check pre-conditions
				// before executing
				throw new IllegalStateException();
			}
			s.getColumn().set(index, args[1]);
			s.addConcept(new BWConcepts.HandEmpty(null));
		}

		@Override
		public void checkPrecondition(State state) {
			// TODO Auto-generated method stub

		}
	}

	/**
	 * Defines Skill: UnStack
	 * @author kerengu
	 *
	 */
	public static class UnStack extends Skill {

		public UnStack(){
			
		}
		
		public UnStack(String[] args) {
			super("UnStack", args);

			// Specify Precondition
			this.precondition = new BWConcepts.UnStackable(args);

			// Specify effects
			this.effects = new ArrayList<Condition>();
			this.effects.add(new BWCondition("Clear", Arrays.copyOfRange(args,
					1, 2)));
			this.effects.add(new BWCondition("Holding", Arrays.copyOfRange(
					args, 0, 1)));
		}

		@Override
		public void execute(State state) {
			BWState s = (BWState) state;
			int index = s.getColumn().indexOf(args[0]);
			if (s.getColumn().get(index + 1) != null) {
				// Shouldn't reach here because we must check pre-conditions
				// before executing
				throw new IllegalStateException();
			}
			s.getColumn().set(index, null);
			s.removeConcept(new BWConcepts.HandEmpty(null));
		}

		@Override
		public void checkPrecondition(State state) {
			// TODO Auto-generated method stub

		}
	}

	/**
	 * Defines Skill: PutDown
	 * 
	 * @author kerengu
	 * 
	 */
	public static class PutDown extends Skill {

		public PutDown(){
			
		}
		public PutDown(String[] args) {
			super("PutDown", args);

			// Specify Precondition
			this.precondition = new BWConcepts.PutDownable(args);

			// Specify Effects
			this.effects = new ArrayList<Condition>();
			this.effects.add(new BWCondition("HandEmpty", null));
		}

		@Override
		public void execute(State state) {
			BWState s = (BWState) state;
			s.addConcept(new BWConcepts.HandEmpty(null));
		}

		@Override
		public void checkPrecondition(State state) {
			// TODO Auto-generated method stub

		}
	}
}
