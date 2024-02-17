
public class PriorityClarckUnboundedQueue extends Queue<Customer> {
	
	//special queue which gives priority to customers with bikes
	public PriorityClarckUnboundedQueue() {
		super();
	}
	
	//extract by priority- bikes first
	public synchronized Customer extract() {

		while (queue.isEmpty() && !isDayOver) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		boolean customerElectricVehical;
		
		//do while queue is not empty 
		for(int i= 0; i< this.queue.size() && !queue.isEmpty(); i++) {
			customerElectricVehical = this.queue.elementAt(i).getElectricVehicalType();
			
			//if Customer's vehicle is bike - extract him first in queue
			if(customerElectricVehical) {
				Customer c = this.queue.elementAt(i);
				this.queue.remove(c);
				return c;
				}
		}
		
		//if all customers in queue has scooters
		return super.extract();
	}

}
