import java.util.Vector;

public class CustomerManager extends Employee implements Runnable {

	private Queue<Customer> customerManagerQueue;
	private Queue<SummaryDetails> cashierQueue;
	private Queue<Customer> SalesRepresentativeQueue;
	private Queue<Customer> seniorTechnicianQueue;
	private int expectedCustomers;
	private Vector<Employee> employees; // manager gets the list of all store's employees including the Cashier
	private Vector <Queue> storeQueues;

	public CustomerManager(String name, int expectedCustomers, Vector<Employee> employees, Vector <Queue> storeQueues) {
		super(name);
		this.customerManagerQueue = storeQueues.elementAt(4);
		this.cashierQueue = storeQueues.elementAt(5);
		this.SalesRepresentativeQueue = storeQueues.elementAt(3);
		this.seniorTechnicianQueue = storeQueues.elementAt(2);
		this.isDayOver = customerManagerQueue.getIsDayOver();
		this.expectedCustomers = expectedCustomers;
		this.employees = employees;
		this.storeQueues=storeQueues;
	}

	public void run() {

		Cashier cash = findCashier(); 

		// while not all the customers arrived to store
		while (cash.getNumOfCashierCustomers() < expectedCustomers) {

			Customer c = this.customerManagerQueue.extract();
			if (c != null) {
				c.setVisitedManager(true); // update that customer has visited manger

				if (c instanceof TechnicalCustomer) {
					managerServiceForTechnicalCustomers((TechnicalCustomer) c);
				}

				else if (c instanceof buyCustomer) {
					managerServiceForBuyCustomers((buyCustomer) c);
				}
			}
		}
		
		endTheDay();//when all expected customers arrived to store- end the day
	}

	private void managerServiceForTechnicalCustomers(TechnicalCustomer c) {

		String serviceType = "repairment";
		double cuurentPriceOffer = c.getPriceOffer();
		double rand = Math.random();

		// give discount and move to seniorTechnician Queue
		if (rand <= 0.1) {
			c.setPriceOffer(cuurentPriceOffer - 50);
			this.seniorTechnicianQueue.insert(c);
		}

		// move to seniorTechnician Queue customers which conviced with current price
		else if (rand > 0.1 && rand <= 0.7) {
			this.seniorTechnicianQueue.insert(c);
		}

		// the rest of the customers won't fix in our store- move to cashier without
		// payment
		else {
			c.setPriceOffer(0);
			moveToCashier(c,serviceType);
		}
	}

	//manager trys to convince customer to buy with discount or sends to cashier without buying  
	private void managerServiceForBuyCustomers(buyCustomer c) {
		
		String serviceType = "purchesing";
		double rand = Math.random();
		double cuurentPriceOffer = c.getPriceOffer();

		//customers which convinced to buy with discount, returns to Sales Representative Queue
		if (rand <= 0.7) {
			c.setPriceOffer(cuurentPriceOffer - 100);
			this.SalesRepresentativeQueue.insert(c);
		}

		//the rest of customers sent to cashier without buying
		else {
			c.setPriceOffer(0);
			c.getCustomerElectricVehicle().backTostock(); //if customer decided not to buy the vehicle- update units in stock
			c.setCustomerElectricVehicle(null); //update that customer didn't took the vehicle
			moveToCashier(c, serviceType);//create summaryDetails and move to cashier
		}

	}

	private void moveToCashier(Customer c, String serviceType) {

		SummaryDetails s = createNewSummaryDetails(c, serviceType); // creating NewSummaryDetails and
		this.cashierQueue.insert(s); // insert to cashier queue

	}

	private SummaryDetails createNewSummaryDetails(Customer c, String serviceType) {

		SummaryDetails s = new SummaryDetails(c, this, true, c.getPriceOffer(),
				serviceType);
		return s;
	}

	//finds cashier in employees vector
	private Cashier findCashier() {

		for (int i = 0; i < this.employees.size(); i++) {
			if (this.employees.elementAt(i) instanceof Cashier) {
				Cashier cash = (Cashier) this.employees.elementAt(i);
				return cash;
			}
		}
		return null;
	}

	//update isDayOver to true!
	private void endTheDay() {
		
		//update employees to go home
		for (int i = 0; i < this.employees.size(); i++) {
			this.employees.elementAt(i).setIsDayOver(true);
		}
		
		//update queues isDayOver
		for (int i = 0; i < this.storeQueues.size(); i++) {
			this.storeQueues.elementAt(i).setDayOver();
		}
	}

}
