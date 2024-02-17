
public class buyCustomer extends Customer {
	
	private ElectricVehicle customerElectricVehicle;

	public buyCustomer (String name, int arrivalTime, boolean ElectricVehical, Queue <Customer> clerckQueue) throws InterruptedException {
		
		super(name,arrivalTime, ElectricVehical,clerckQueue);

	}

	public ElectricVehicle getCustomerElectricVehicle() {
		return customerElectricVehicle;
	}

	//after customer buys ElectricVehicle - set it to be his own
	public void setCustomerElectricVehicle(ElectricVehicle customerElectricVehicle) {
		this.customerElectricVehicle = customerElectricVehicle;
	}

}
