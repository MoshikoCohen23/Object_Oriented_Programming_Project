
public class JuniorTechnician extends Employee implements Runnable {

	int yearsOfExperience;
	protected BoundedQueue<Customer> juniorTechnicalQueue; // max capacity is 5
	protected Queue<Customer> seniorTechnicianQueue;
	protected Queue<Customer> customerManagerQueue;
	protected Queue<SummaryDetails> cashierQueue;
	private double technicalInspectionTime;

	//Constructor
	public JuniorTechnician(String name, int yearsOfExperience, double technicalInspectionTime,
			BoundedQueue<Customer> juniorTechnicalQueue, Queue<Customer> seniorTechnicianQueue,
			Queue<Customer> customerManagerQueue, Queue<SummaryDetails> cashierQueue) {
		super(name);
		this.juniorTechnicalQueue = juniorTechnicalQueue;
		this.seniorTechnicianQueue = seniorTechnicianQueue;
		this.customerManagerQueue = customerManagerQueue;
		this.cashierQueue = cashierQueue;
		this.isDayOver = juniorTechnicalQueue.getIsDayOver();
		this.yearsOfExperience = yearsOfExperience;
		this.technicalInspectionTime = technicalInspectionTime;
	}

	public void run() {

		while (!this.isDayOver) {

			Customer c = this.juniorTechnicalQueue.extract(); //extract customer from queue
			if (c != null) {

				generalInspection(c); // performing general Inspection - time of general inspection was recived from user in GUI

				double rand = Math.random();
				// 20% of customers detect complicated problem and being sent to Senior technicians
				if (rand < 0.2) {
					this.seniorTechnicianQueue.insert(c);
				}

				// 10% of customers are unpleased and being sent to manager
				else if (rand >= 0.2 && rand < 0.3) {
					this.customerManagerQueue.insert(c);
				}

				//rest of customers are getting technical service
				else {
					juniorTechnicalService(c); // Provide the technical service
					moveToCashier(c); //create summaryDetails and move to cashier queue
				}
			}
		}

	}

	protected void moveToCashier(Customer c) {

		SummaryDetails s = createNewSummaryDetails(c); // creating NewSummaryDetails and
		this.cashierQueue.insert(s); // insert to SummaryDetails queue

	}

	// create new SummaryDetails according to case
	protected SummaryDetails createNewSummaryDetails(Customer c) {
		SummaryDetails s = new SummaryDetails(c, this, c.visitedManager, c.getPriceOffer(), "repairment");
		return s;
	}

	//performing general inspection - time is according to user's choice
	private void generalInspection(Customer c) {

		try {
			Thread.sleep((long) this.technicalInspectionTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void juniorTechnicalService(Customer c) {
		try {
			Thread.sleep(3000);// service Time
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		double rand = Math.random();
		double cost = 100 + 700 * rand; // cost can be between 100-800
		c.setPriceOffer(cost);// updating customer's service price offer
		c.setTotalPayment(cost); // updating customer's total payment
		this.profitToStore += cost; // updating juniorTechnical profit to store
		this.numOfCustomerServed++;
	}

}
