package blocksworld;

import java.util.ArrayList;
import java.util.Arrays;

import util.ComplexConcept;
import util.Concept;
import util.Condition;
import util.PrimitiveConcept;

public class BWConcepts {

	public static class On extends PrimitiveConcept {

		public On() {
		}

		public On(String[] args) {
			super("On", args);
			// Set the condition
			this.setCondition(new BWCondition("On", args));
		}

	}

	public static class Clear extends PrimitiveConcept {

		public Clear() {
		}

		public Clear(String[] args) {
			super("Clear", args);
			// Set the condition
			this.setCondition(new BWCondition("Clear", args));

		}

	}

	public static class HandEmpty extends PrimitiveConcept {

		public HandEmpty() {
		}

		public HandEmpty(String[] args) {
			super("HandEmpty", args);
			// Set the condition
			this.setCondition(new BWCondition("HandEmpty", args));
		}

	}

	public static class UnStackable extends ComplexConcept {

		public UnStackable() {
		}

		public UnStackable(String[] args) {
			super("UnStackable", args);
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
			super("Stackable", args);
			// Define the positives
			this.positives = new ArrayList<Concept>();
			// TODO: fill in
		}
	}

	public static class PutDownable extends ComplexConcept {
		public PutDownable() {
		}

		public PutDownable(String[] args) {
			super("PutDownable", args);
			this.positives = new ArrayList<Concept>();
			// TODO: fill in

			this.negatives = new ArrayList<Concept>();
			this.negatives.add(new BWConcepts.HandEmpty(null)); // TODO: Get rid of negatives
		}
	}
}
