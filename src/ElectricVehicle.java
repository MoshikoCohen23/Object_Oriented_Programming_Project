
abstract public class ElectricVehicle implements Comparable <ElectricVehicle> {
	
	protected String model;
	protected int maxSpeed;
	protected int weight;
	protected double price;
	protected int unitsInStock;
	protected boolean ElectricVehicleType;
	
	//Constructor
	public ElectricVehicle(String model,int maxSpeed,int weight, double price, int unitsInStock) {
		this.model=model;
		this.maxSpeed=maxSpeed;
		this.weight=weight;
		this.price=price;
		this.unitsInStock=unitsInStock;
	}
	
	//Constructor without units in stock and price
	public ElectricVehicle(String model,int maxSpeed,int weight) {
		this.model=model;
		this.maxSpeed=maxSpeed;
		this.weight=weight;
	}
	
	//comparing between vehicle by price
	public int compareTo (ElectricVehicle other) {
		
		if(this.price>other.price)
			return 1;
		
		else if (this.price<other.price)
			return -1;
		
		else
			return 0;
	}
	
	//if customer decided not to buy the Vehicle- update units in stock
	public void backTostock() {
		this.unitsInStock = this.unitsInStock+1;
	}
	
	public void setUnitsInStock () {
		if(this.unitsInStock > 0)
			this.unitsInStock = this.unitsInStock-1;
	}
	
	public int getUnitsInStock() {
		return this.unitsInStock;
	}
	
	public boolean getElectricVehicleType() {
		return this.ElectricVehicleType;
	}
	
	public double getElectricVehiclePrice() {
		return this.price;
	}
	

}
