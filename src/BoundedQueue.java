import java.util.Vector;

public class BoundedQueue <T> extends Queue<T> {

	private int maxSize; //max capacity of queue
	private Vector<T> queue; 
	private boolean isDayOver = false;

	public BoundedQueue(int maxSize) {

		this.queue = new Vector<T>();
		this.maxSize = maxSize;
	}

	public synchronized void insert(T item) {
		
		//cannot insert items into queue over the max capacity
		while (queue.size() >= maxSize)
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		queue.add(item);
		notifyAll(); 
	}

	public synchronized T extract() {
		
		//cannot extract items from queue if it is empty and day is not over
		while (queue.isEmpty() && !isDayOver)
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		
		if(!queue.isEmpty()) {
		T ans = queue.elementAt(0);
		queue.remove(ans);
		notifyAll(); //notify all threads that are waiting to insert into the queue
		return ans;}
		
		else return null;
	}
	

}
