
public class Clerck extends Employee implements Runnable {

	private PriorityClarckUnboundedQueue clercksQueue;
	private BoundedQueue<Customer> juniorTechnicalQueue;
	private Queue<Customer> salesRepresentiveQueue;
	boolean isDayOver;

	//Constructor
	public Clerck(String name, PriorityClarckUnboundedQueue clercksQueue, BoundedQueue<Customer> juniorTechnicalQueue,
			Queue<Customer> salesRepresentiveQueue) {
		super(name);
		this.clercksQueue = clercksQueue;
		this.juniorTechnicalQueue = juniorTechnicalQueue;
		this.salesRepresentiveQueue = salesRepresentiveQueue;
		this.isDayOver = clercksQueue.getIsDayOver();

	}

	public void run() {

		while (!isDayOver) {
			Customer c;
			c = this.clercksQueue.extract(); // extract customer from queue with priority to customers with bikes
			
			if (c != null) {
				double rand = Math.random();
				double clerckServiceTime = 3000 + 3000 * rand; // clerckServiceTime can be between 3000-6000
				try {
					Thread.sleep((long) (clerckServiceTime));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				// move to next queue according to customer's type
				if (c instanceof buyCustomer) {
					this.salesRepresentiveQueue.insert(c);
				}

				else if (c instanceof TechnicalCustomer)
					this.juniorTechnicalQueue.insert(c);
			}
		}

	}

}
