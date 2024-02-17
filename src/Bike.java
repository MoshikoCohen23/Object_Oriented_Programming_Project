
public class Bike extends ElectricVehicle {

	private boolean isClothing; 

	//Constructor
	public Bike(String model, int maxSpeed, int weight, double price, int unitsInStock, boolean isClothing) {
		super(model, maxSpeed, weight, price, unitsInStock);
		this.isClothing = isClothing;
		this.ElectricVehicleType = true;
	}

	//Constructor without units in stock and price
	public Bike(String model, int maxSpeed, int weight, boolean isClothing) {
		super(model, maxSpeed, weight);
		this.isClothing = isClothing;
		this.ElectricVehicleType = true;

	}
}
