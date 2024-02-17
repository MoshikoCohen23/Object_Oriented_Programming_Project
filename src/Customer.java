
abstract public class Customer implements Runnable {

	protected Queue <Customer> clercksQueue;
	protected String name;
	protected int arrivalTime;
	protected boolean ElectricVehicalType; // true for bikes and false for scooters
	protected boolean visitedManager; 
	protected double priceOffer;
	protected double totalPayment; //total final payment of customer after discount
	

	public Customer(String name, int arrivalTime, boolean ElectricVehicalType, Queue <Customer> clercksQueue) throws InterruptedException {

		this.name = name;
		this.arrivalTime = arrivalTime;
		this.ElectricVehicalType = ElectricVehicalType;
		this.clercksQueue = clercksQueue;
		Thread t = new Thread(this); //for each customer being created - create new thread and insert to Queue
		t.start();
	}
	
	public void run()  {
		
		try {
			Thread.sleep(this.arrivalTime); //each customer arrives to store according to its arrival time
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		this.clercksQueue.insert(this); //insert to the first queue in store- the clercks queue
	}
	
	
	//getters and setters
	public void setVisitedManager(boolean visited) {
		this.visitedManager=visited;
	}
	
	public boolean getVisitedManager() {
		return this.visitedManager;
	}
	
	public void setPriceOffer(double newOffer) {
		
		this.priceOffer=newOffer;
	}
	
	public double getPriceOffer() {
		return this.priceOffer;
	}
	
	public boolean getElectricVehicalType() {
		return this.ElectricVehicalType;
	}
	
	public String getName() {
		return this.name;
	}

	public double getTotalPayment() {
		return totalPayment;
	}

	public void setTotalPayment(double totalPayment) {
		this.totalPayment += totalPayment;
	}
	
}
