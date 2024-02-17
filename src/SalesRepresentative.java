import java.util.Vector;

public class SalesRepresentative extends Employee implements Runnable {

	private Queue<Customer> SalesRepresentativeQueue;
	private Queue<Customer> customerManagerQueue;
	private Queue<SummaryDetails> cashierQueue;
	private Vector<ElectricVehicle> stock;

	//Constructor
	public SalesRepresentative(String name, Queue<Customer> SalesRepresentativeQueue,
			Queue<Customer> customerManagerQueue, Queue<SummaryDetails> cashierQueue, Vector<ElectricVehicle> stock) {
		super(name);

		this.SalesRepresentativeQueue = SalesRepresentativeQueue;
		this.customerManagerQueue = customerManagerQueue;
		this.cashierQueue = cashierQueue;
		this.stock = stock;
		this.isDayOver = SalesRepresentativeQueue.getIsDayOver();
	}

	public void run() {

		while (!this.isDayOver) {

			Customer c = this.SalesRepresentativeQueue.extract();
			if (c != null) {

				ElectricVehicle e = chooseMinElectricVehicle(this.stock, c.getElectricVehicalType()); // find the cheapest requested vehicle in stock														
				((buyCustomer) c).setCustomerElectricVehicle(e); //"give" the customer its chosen ElectricVehicle
				double pleasedCustomerRate = Math.random(); 

				// if customer arrived from Clerck queue (not from manager)
				if (!c.getVisitedManager()) {
					c.setPriceOffer(e.getElectricVehiclePrice()); // update price offer for customer according the chosen ElectricVehicle

					// unpleased customers are sent to manager
					if (pleasedCustomerRate <= 0.13) {
						this.customerManagerQueue.insert(c);
					}
				}

				// customers which arrived from manager or customers which were pleased from price offer
				if (c.getVisitedManager() || (!c.getVisitedManager() && pleasedCustomerRate > 0.13)) {
					conversationWithCustomer(c); // performing conversation With Customer
					saleService((buyCustomer) c, e); // sell the vehicle and update payments
					moveToCashier(c); // create new summeryDetails and sent customer to cashier
				}
			}
		}
	}

	private void conversationWithCustomer(Customer c) {
		if (c.getVisitedManager()) {
			try {
				Thread.sleep(1500);// conversation with customers arrived from manager
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		}

		else {
			try {
				Thread.sleep(3000);// conversation with customers which didn't arrive from manager 
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		}
	}

	private void moveToCashier(Customer c) {
		SummaryDetails s = createNewSummaryDetails(c); // creating NewSummaryDetails and
		this.cashierQueue.insert(s); // insert to cashier queue
		this.numOfCustomerServed++;

	}

	private SummaryDetails createNewSummaryDetails(Customer c) {
			
		 SummaryDetails s = new SummaryDetails(c, this, c.getVisitedManager(), c.getPriceOffer(), "purchesing");
		
		return s;
	}

	// sell Vehicle to customer
	private void saleService(buyCustomer c, ElectricVehicle e) {
		//e.setUnitsInStock();
		//c.setCustomerElectricVehicle(e);
		c.setTotalPayment(c.priceOffer); //set customer total final payment
		this.setProfitToStore(e.getElectricVehiclePrice()); //set employee profit to store according to the ElectricVehiclePrice
	}

	private synchronized ElectricVehicle chooseMinElectricVehicle(Vector<ElectricVehicle> stock, boolean RequestedElectricVehicleType) {
		
		// find the first appearance of RequestedElectricVehicleType on stock (and available for purchasing)
		int firstIndex = findFirstVehicleIndex(stock, RequestedElectricVehicleType); 
		ElectricVehicle min = stock.elementAt(firstIndex);
		
		// search requested Vehicle with min price starting from first index, compare only vehicles available for purchasing
		for (int i = firstIndex; i < stock.size(); i++) {
			ElectricVehicle e = stock.elementAt(i);
			if ((e.getElectricVehicleType() == RequestedElectricVehicleType) && e.getUnitsInStock() > 0) {
				if (e.compareTo(min) < 0)
					min = e;
			}
		}
		min.setUnitsInStock(); //update the chosen vehicle's units in stock
		return min;
	}

	private int findFirstVehicleIndex(Vector<ElectricVehicle> stock, boolean RequestedElectricVehicleType) {

		for (int i = 0; i < stock.size(); i++) {

			if ((stock.elementAt(i).getElectricVehicleType() == RequestedElectricVehicleType) && stock.elementAt(i).getUnitsInStock()>0)
				return i;
		}

		return -1;
	}
	
}
