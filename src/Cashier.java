import java.util.Comparator;
import java.util.Vector;

public class Cashier extends Employee implements Runnable {

	private Queue<SummaryDetails> cashierQueue;
	private Vector<SummaryDetails> SummaryDetailsData = new Vector<SummaryDetails>();
	private Queue<Customer> customerManagerQueue;
	private Vector<Employee> employees;

	public Cashier(String name, Queue<SummaryDetails> cashierQueue, Queue<Customer> customerManagerQueue,
			Vector<Employee> employees) {
		super(name);
		this.cashierQueue = cashierQueue;
		this.customerManagerQueue = customerManagerQueue;
		this.employees = employees;
	}

	public void run() {

		while (!this.isDayOver) {

			try {
				Thread.sleep(1000); // Documentation time
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			SummaryDetails s = this.cashierQueue.extract();
			if (s != null) {
				SummaryDetailsData.add(s);

				// in each Documentation we will send null customer to manager to "wake" the
				// manager thread
				Customer c = null;
				this.customerManagerQueue.insert(c);
			}
		}

		// after the day ends print summery for the day
		printSummery();
		printExellentEmployees();
	}

	public void printSummery() {

		System.out.println("Summery Report:");
		System.out.println("Number of customers which recived service in store: " + this.SummaryDetailsData.size());
		int sumOfprofits = 0;
		for (int i = 0; i < this.SummaryDetailsData.size(); i++) {
			Customer c = SummaryDetailsData.elementAt(i).getCustomer();
			System.out.println("Customer: " + c.getName() + " price: " + c.getTotalPayment());
			sumOfprofits += SummaryDetailsData.elementAt(i).getSummaryDetailsPayment();
		}

		System.out.println("Profit of store: " + sumOfprofits);
		System.out.println();
	}

	private void printExellentEmployees() {

		JuniorTechnician execllentTech = findExellentTechnicalEmployee(employees,
				new employeesByNumOfCustomerCoparator());
		System.out.println("Exellent Technical Employee: " + execllentTech.getName());

		SalesRepresentative execllentSale = findExellentSalesRepresentativeEmployee(employees,
				new employeesByProfitToStoreComparator());
		System.out.println("Exellent Sales Representative Employee: " + execllentSale.getName());

	}

	//finds ExellentTechnicalEmployee and compare by the num of customers the employee served
	private JuniorTechnician findExellentTechnicalEmployee(Vector<Employee> employees, Comparator c) {

		//initiating Excellent Junior Technician to be the first Technician in employees list
		JuniorTechnician maxE = (JuniorTechnician) employees.elementAt(findMinIndex(employees, "Technical"));

		for (int i = 0; i < employees.size(); i++) {

			if (employees.elementAt(i) instanceof JuniorTechnician) {
				if (c.compare(employees.elementAt(i), maxE) > 0)
					maxE = (JuniorTechnician) employees.elementAt(i);
			}
		}

		return maxE;
	}
	//finds SalesRepresentative and compare by Profit To Store 
	private SalesRepresentative findExellentSalesRepresentativeEmployee(Vector<Employee> employees, Comparator c) {

		//initiating Excellent Sales Representative to be the first Sales Representative in employees list
		SalesRepresentative maxE = (SalesRepresentative) employees.elementAt(findMinIndex(employees, "Sale"));

		for (int i = 0; i < employees.size(); i++) {

			if (employees.elementAt(i) instanceof SalesRepresentative) {
				if (c.compare(employees.elementAt(i), maxE) > 0)
					maxE = (SalesRepresentative) employees.elementAt(i);
			}
		}

		return maxE;
	}

	//finds the first index of employee from the type sent
	private int findMinIndex(Vector<Employee> employees, String EmployeeType) {

		for (int i = 0; i < employees.size(); i++) {
			if (employees.elementAt(i) instanceof JuniorTechnician && EmployeeType.equals("Technical")) {
				return i;
			}

			else if (employees.elementAt(i) instanceof SalesRepresentative && EmployeeType.equals("Sale"))
				return i;
		}

		return -1; // if employee not found
	}

	public int getNumOfCashierCustomers() {
		return this.SummaryDetailsData.size();
	}

}
