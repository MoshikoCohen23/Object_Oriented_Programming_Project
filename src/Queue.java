import java.util.Vector;

public class Queue<T> {

	protected Vector<T> queue;
	protected boolean isDayOver = false; //indicator for the work day status

	// constractor
	public Queue() {
		this.queue = new Vector<T>();
	}

	public synchronized void insert(T item) {

		queue.add(item);
		this.notifyAll();
	}

	public synchronized T extract() {

		//if the day is not over but queue is empty- wait until day is over or until more customers are being inserted to queue
		
		while (queue.isEmpty() && !isDayOver)
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		if (!queue.isEmpty()) {
			T t = queue.elementAt(0);
			queue.remove(t);
			return t;
		}

		else
			return null;
	}
	
	public synchronized void setDayOver() {
		this.isDayOver = true;
		notifyAll();
	}
	
	public boolean getIsDayOver() {
		return this.isDayOver;
	}
	
 

}
