package blocksworld;

import java.util.ArrayList;
import java.util.List;

import util.Goal;
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
		
		public Stack(ArrayList<String> args) {
			super("Stack", args);

			// Specify Precondition
			this.precondition = new Goal("stackable", args);

			// Specify effects
			this.effects = new ArrayList<Goal>();
			this.effects.add(new Goal("on", args));
			this.effects.add(new Goal("hand-empty", null));
		}

		@Override
		public void execute(State state) {
//			BWState s = (BWState) state;
//			int index = s.getColumn().indexOf(args.get(0)) + 1;
//			if (s.getColumn().get(index) != null) {
//				// Shouldn't reach here because we must check pre-conditions
//				// before executing
//				throw new IllegalStateException();
//			}
//			s.getColumn().set(index, args.get(1));
//			s.addConcept(new BWConcepts.HandEmpty(null));
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
		
		public UnStack(ArrayList<String> args) {
			super("UnStack", args);

			// Specify Precondition
			this.precondition = new Goal("unstackable", args);

			// Specify effects
			this.effects = new ArrayList<Goal>();
			this.effects.add(new Goal("clear", args.subList(1, 2)));
			this.effects.add(new Goal("holding", args.subList(0, 1)));
		}

		@Override
		public void execute(State state) {
//			BWState s = (BWState) state;
//			int index = s.getColumn().indexOf(args.get(0));
//			if (s.getColumn().get(index + 1) != null) {
//				// Shouldn't reach here because we must check pre-conditions
//				// before executing
//				throw new IllegalStateException();
//			}
//			s.getColumn().set(index, null);
//			s.removeConcept(new BWConcepts.HandEmpty(null));
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
		public PutDown(ArrayList<String> args) {
			super("PutDown", args);

			// Specify Precondition
			this.precondition = new Goal("putdownable", args);

			// Specify Effects
			this.effects = new ArrayList<Goal>();
			this.effects.add(new Goal("hand-empty", null));
		}

		@Override
		public void execute(State state) {
//			BWState s = (BWState) state;
//			s.addConcept(new BWConcepts.HandEmpty(null));
		}

		@Override
		public void checkPrecondition(State state) {
			// TODO Auto-generated method stub
		}
		
		@Override
		public Goal getPrecondition(){
		    if (args.get(1).charAt(0) == 'T'){
		        return new Goal("holding", args.subList(0, 1));
		    }
		    return this.precondition;
		}
	}
}
