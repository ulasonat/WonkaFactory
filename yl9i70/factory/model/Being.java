package factory.model;

public abstract class Being {
	protected int code;
	protected String name;
	protected static int counter = 0; // this will increment each time an instance of class that extends Being class created, and the value will be the code, so each instance will have an unique code.

	public Being(String name) {
		this.name = name;
		this.code = ++counter;
	}
	
	@Override
	public String toString() {
		return String.valueOf(code) + ", " + name;
	}
	
	@Override
	public boolean equals(Object o) {
		if(this == o)
			return true;
		
		if(o == null || this.getClass() != o.getClass())
			return false;
		
		Being o2 = (Being) o;
		
		return (this.name.equals(o2.getName()));
	}

	public int getCode() {
		return code;
	}
	
	public String getName() {
		return name;
	}

	/** getCounter method is static, therefore we can access this method which gives the number of beings without having an instance of the class to be called from.*/
	public static int getCounter() {
		return counter;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	// there will be no setters for code and counter, because those should not be changed once initialized.
}