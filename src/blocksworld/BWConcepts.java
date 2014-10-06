package blocksworld;

import java.util.ArrayList;
import java.util.Arrays;

import util.ComplexConcept;
import util.Concept;
import util.Condition;

public class BWConcepts {

	public static class On extends Concept {

		public On() {
		}

		public On(String[] args) {
			super("on", args);
			// Set the condition
			this.setCondition(new BWCondition("on", args));
		}

	}

	public static class Clear extends Concept {

		public Clear() {
		}

		public Clear(String[] args) {
			super("clear", args);
			// Set the condition
			this.setCondition(new BWCondition("clear", args));

		}

	}

	public static class HandEmpty extends Concept {

		public HandEmpty() {
		}

		public HandEmpty(String[] args) {
			super("hand-empty", args);
			// Set the condition
			this.setCondition(new BWCondition("hand-empty", args));
		}

	}

	public static class UnStackable extends ComplexConcept {

		public UnStackable() {
		}

		public UnStackable(String[] args) {
			super("unstackable", args);
			
			// Set the condition
			this.setCondition(new BWCondition("unstackable", args));
			
			// Define the positives
			this.positives = new ArrayList<Concept>();
			this.positives.add(new BWConcepts.On(args));
			this.positives.add(new BWConcepts.Clear(Arrays.copyOfRange(
					args, 0, 1)));
			this.positives.add(new BWConcepts.HandEmpty(null));
		}
	}

	public static class Stackable extends ComplexConcept {

		public Stackable() {
		}

		public Stackable(String[] args) {
			super("stackable", args);
			// Define the positives
			this.positives = new ArrayList<Concept>();
			// TODO: fill in
		}
	}

	public static class PutDownable extends ComplexConcept {
		public PutDownable() {
		}

		public PutDownable(String[] args) {
			super("putdownable", args);
			this.positives = new ArrayList<Concept>();
			// TODO: fill in

			this.negatives = new ArrayList<Concept>();
			this.negatives.add(new BWConcepts.HandEmpty(null)); // TODO: Get rid of negatives
		}
	}
}
