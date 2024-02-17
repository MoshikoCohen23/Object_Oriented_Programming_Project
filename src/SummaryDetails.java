
public class SummaryDetails {

	//private Queue<SummaryDetails> SummaryDetailsQueue; // SummaryDetails queue
	private Customer customer;
	private Employee employee;
	private boolean visitedManager;
	private double payment;
	private String serviceType;

	public SummaryDetails(Customer customer, Employee employee, boolean visitedManager,
			double payment, String serviceType) {

		this.setCustomer(customer);
		this.setEmployee(employee);
		this.visitedManager=visitedManager;
		this.payment=payment;
		this.serviceType=serviceType;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public double getSummaryDetailsPayment() {
		return payment;
	}
}
