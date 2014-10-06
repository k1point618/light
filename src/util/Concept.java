package util;

public abstract class Concept {

	protected String name;
	protected String[] args;
	protected Condition condition;

	public Concept() {

	}

	public Concept(String name, String[] args) {
		this.name = name;
		this.args = args;
	}

	public void setHead(String name, String[] args) {
		this.name = name;
		this.args = args;
	}
	
	public void setCondition(Condition cond){
	    this.condition = cond;
	}

	public Condition getCondition(){
	    return this.condition;
	}
	
}
