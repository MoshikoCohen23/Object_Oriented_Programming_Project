import java.util.Comparator;

public class employeesByNumOfCustomerCoparator  implements Comparator<Employee> {
	
	public int compare(Employee e1, Employee e2) {
		
		return e1.getnumOfCustomerServed()-e2.getnumOfCustomerServed();
	}

}
