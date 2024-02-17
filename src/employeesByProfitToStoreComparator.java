import java.util.Comparator;

public class employeesByProfitToStoreComparator implements Comparator<Employee> {

	public int compare(Employee e1, Employee e2) {
		return (int) (e1.getProfitToStore()-e2.getProfitToStore());
	}

}
