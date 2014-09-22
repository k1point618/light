package util;

public class PrimitiveConcept extends Concept{
	
	protected Condition condition;
	
	public PrimitiveConcept(){
		super();
	}
	
	public PrimitiveConcept(String name, String[] args){
		super(name, args);
	}
	
	public void setCondition(Condition condition){
		this.condition = condition;
	}
	
	public Condition getCondition(){
		return condition;
	}

}
