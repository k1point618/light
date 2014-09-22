package util;

public abstract class Concept {

	protected String name;
	protected String[] args;

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

	
}
