
public class TechnicalCustomer extends Customer {

	private ElectricVehicle customerElectricVehicle;//technical customers come to store with their own Electric Vehicle

	public TechnicalCustomer(String name, int arrivalTime,boolean ElectricVehical,
			Queue<Customer> clerckQueue, String model, int maxSpeed, int weight, boolean isClothing) throws InterruptedException {
		
		super(name,arrivalTime, ElectricVehical,clerckQueue);
		
		//if customer has bikes
		if(ElectricVehical) 
			this.customerElectricVehicle = new Bike(model,maxSpeed,weight,isClothing);
		
		else
			this.customerElectricVehicle = new Scooter(model,maxSpeed,weight);

	}

	public ElectricVehicle getCustomerElectricVehicle() {
		return customerElectricVehicle;
	}

}
