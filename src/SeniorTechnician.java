
public class SeniorTechnician extends JuniorTechnician implements Runnable {

	//we assumes that every SeniorTechnician was a JuniorTechnician. They are both share the same fields
	private boolean isMaster;

	public SeniorTechnician(String name, int yearsOfExperience, boolean isMaster, BoundedQueue<Customer> juniorTechnicalQueue,
			Queue<Customer> seniorTechnicianQueue, Queue<Customer> customerManagerQueue,
			Queue<SummaryDetails> cashierQueue) {

		//SeniorTechnician does not perform general inspection - therefore inspection time is 0
		super(name, yearsOfExperience,0, juniorTechnicalQueue, seniorTechnicianQueue, customerManagerQueue, cashierQueue);
		this.isMaster = isMaster;
		this.isDayOver = seniorTechnicianQueue.getIsDayOver();
	}

	public void run() {

		while (!isDayOver) {
			
			Customer c = this.seniorTechnicianQueue.extract();

			if(c != null) {
			// if arrived from manager
			if (c.getVisitedManager()) {
				technicalServiceCustomersFromManger(c);//providing technical service for customers arrived from manager
			}

			else {
				// if customer arrived from junior technician
				try {
					Thread.sleep(1000); // technical cost evaluation
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				seniorTechnicalService(c); //providing technical service
			}
			}
		}

	}
	
	//providing technical service for customers arrived from manager
	private void technicalServiceCustomersFromManger(Customer c) {
		try {
			Thread.sleep(1000); // Service time 
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.numOfCustomerServed++;
		moveToCashier(c); //Create summaryDetails and move to cashier
	}

	//providing technical service and choose service time
	private void seniorTechnicalService(Customer c) {
		double cost = seniorTechnicalCost(c);// calculating service cost according to customer's Vehical type

		// 90% from customers with cost over 450 are unpleased and sent to manager
		if (!this.isMaster && cost > 450 && !c.getVisitedManager()) {
			double rand = Math.random();
			if (rand <= 0.9) {
				this.customerManagerQueue.insert(c);
			}
		}

		// the rest are receiving technical service from senior technician
		else {
			try {
				Thread.sleep(seniorTechnicalTime(cost));// service time according to cost
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			this.profitToStore +=cost; //updating juniorTechnical profit to store
			this.numOfCustomerServed++;
			c.setTotalPayment(cost);// updating customer's total payment
			moveToCashier(c); //Create summaryDetails and send to cashier queue
		}
	}

	//returns seniorTechnical service Time according to service cost
	private int seniorTechnicalTime(double cost) {

		if (cost >= 50 && cost < 300)
			return 1000;
		else if (cost >= 300 && cost < 450)
			return 2000;
		else
			return 3000;
	}

	// calculating service cost according to customer's ElectricVehical type
	private double seniorTechnicalCost(Customer c) {

		double cost;
		// if customer's ElectricVehical is bike
		if (c.getElectricVehicalType()) {
			cost = 100 + 700 * Math.random(); // cost for bikes can be between 100-800
			c.setPriceOffer(cost);// updating customer's service price offer
		}

		else {
			cost = 50 + 450 * Math.random(); // cost for scooters can be between 50-500
			c.setPriceOffer(cost);// updating customer's service price offer
		}

		return cost;
	}

}
