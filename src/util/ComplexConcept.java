package util;

import java.util.List;

public class ComplexConcept extends Concept {

	protected List<Concept> positives;
	protected List<Concept> negatives;
	
	public ComplexConcept(){
		super();
	}
	
	public ComplexConcept(String name, String[] args){
		super(name, args);
	}
	
	public List<Concept> getPositives() {
		return positives;
	}

	public List<Concept> getNegatives() {
		return negatives;
	}

	public void setPositives(List<Concept> positives) {
		this.positives = positives;
	}

	public void setNegatives(List<Concept> negatives) {
		this.negatives = negatives;
	}
}
