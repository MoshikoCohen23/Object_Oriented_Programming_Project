
abstract public class Employee implements Comparable <Employee>{
	
	protected String name;
	protected boolean isDayOver;
	protected double profitToStore;
	int numOfCustomerServed;

		
	public Employee (String name) {
		this.name=name;
		this.isDayOver = false;
		this.profitToStore=0;
		this.numOfCustomerServed = 0;

	}
	
	public void setProfitToStore(double cost) {
		this.profitToStore += cost;
	}
	
	public int getnumOfCustomerServed() {
		return this.numOfCustomerServed;
	}
	
	public double getProfitToStore() {
		return this.profitToStore;
	}
	
	public String getName() {
		return this.name;
	}

	
	//we chose to compare naturally employees according to first letter in name.  
	public int compareTo(Employee other) {
		
		if(this.name.charAt(0)>other.name.charAt(0))
			return 1;
		
		else if (this.name.charAt(0) < other.name.charAt(0))
			return -1;
		
		else
			return 0;
	}
	
	public void setIsDayOver(boolean flag) {
		this.isDayOver=flag;
	}
}
