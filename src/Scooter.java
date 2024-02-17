
public class Scooter extends ElectricVehicle {

	//Constructor
	public Scooter(String model, int maxSpeed, int weight, double price, int unitsInStock) {

		super(model, maxSpeed, weight, price, unitsInStock);
		this.ElectricVehicleType = false;

	}
	//Constructor without units in stock and price
	public Scooter(String model, int maxSpeed, int weight) {
		super(model, maxSpeed, weight);
		this.ElectricVehicleType = false;

	}

}
