import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

public class Store {
	
	private PriorityClarckUnboundedQueue clercksQueue =new PriorityClarckUnboundedQueue ();
	private BoundedQueue <Customer> juniorTechnicalQueue = new BoundedQueue <Customer> (5);
	protected Queue<Customer> seniorTechnicianQueue = new Queue<Customer> ();
	private Queue<Customer> SalesRepresentativeQueue = new Queue<Customer> ();
	private Queue<Customer> customerManagerQueue=new Queue<Customer> ();
	private Queue<SummaryDetails> cashierQueue = new Queue<SummaryDetails>();
	private Vector <Employee> employess = new Vector <Employee> ();
	private Vector <ElectricVehicle> stock = new Vector <ElectricVehicle> ();
	private Vector <Thread> employeesThreads = new Vector <Thread> ();
	private Vector <Queue> storeQueues = new Vector <Queue> ();
	private int numOfExpectedCustomers;

	private String customerFile= "Customers.txt";
	private String stockFile= "Stock.txt";
	
	//Constructor
	public Store(int numOfManagers, double technicalInspectionTime) throws IOException, InterruptedException {
		
		this.stock = CreateStockVector(stockFile);
		this.numOfExpectedCustomers = reciveCustomerToStore(customerFile);
		creatStore(numOfManagers, technicalInspectionTime);	
		startAll();
	}
	
	private void creatStore( int numOfManagers, double technicalInspectionTime) {
		createQueuesVector();
		creatCleckThreads();
		creatJuniorTechnicianThreads(technicalInspectionTime);
		createSeniorTechnicianThreads();
		createSalesRepresentativeThreads();
		createCustomerManagersThreads(numOfManagers);
		createCashierThreads();
	}
	
	private void createQueuesVector() {

		this.storeQueues.add(clercksQueue);
		this.storeQueues.add(juniorTechnicalQueue);
		this.storeQueues.add(seniorTechnicianQueue);
		this.storeQueues.add(SalesRepresentativeQueue);
		this.storeQueues.add(customerManagerQueue);
		this.storeQueues.add(cashierQueue);
	}
	
	private void creatCleckThreads() {

		Clerck c1 = new Clerck("Noam", this.clercksQueue, this.juniorTechnicalQueue, this.SalesRepresentativeQueue);
		Clerck c2 = new Clerck("Maya", this.clercksQueue, this.juniorTechnicalQueue, this.SalesRepresentativeQueue);
		
		this.employess.add(c1);
		this.employess.add(c2);
		this.employeesThreads.add(new Thread(c1));
		this.employeesThreads.add(new Thread(c2));
	}
	
	private void creatJuniorTechnicianThreads(double technicalInspectionTime) {
		
		JuniorTechnician j1 = new JuniorTechnician("Ofir",5,technicalInspectionTime, this.juniorTechnicalQueue, this.seniorTechnicianQueue, this.customerManagerQueue, this.cashierQueue);
		JuniorTechnician j2 = new JuniorTechnician("Yarden",3, technicalInspectionTime, this.juniorTechnicalQueue, this.seniorTechnicianQueue, this.customerManagerQueue, this.cashierQueue);
		JuniorTechnician j3 = new JuniorTechnician("Dolev",7,technicalInspectionTime, this.juniorTechnicalQueue, this.seniorTechnicianQueue, this.customerManagerQueue, this.cashierQueue);
		
		this.employess.add(j1);
		this.employess.add(j2);
		this.employess.add(j3);
		this.employeesThreads.add(new Thread(j1));
		this.employeesThreads.add(new Thread(j2));
		this.employeesThreads.add(new Thread(j3));
		
	}
	
	private void createSeniorTechnicianThreads() {
		
		SeniorTechnician s1 = new SeniorTechnician ("Guy", 25, true, this.juniorTechnicalQueue, this.seniorTechnicianQueue, this.customerManagerQueue,this.cashierQueue);
		SeniorTechnician s2 = new SeniorTechnician ("Adar", 12, false, this.juniorTechnicalQueue, this.seniorTechnicianQueue, this.customerManagerQueue,this.cashierQueue);
		
		this.employess.add(s1);
		this.employess.add(s2);
		this.employeesThreads.add(new Thread(s1));
		this.employeesThreads.add(new Thread(s2));		
	}
	
	private void createSalesRepresentativeThreads() {
		
		SalesRepresentative sale1 = new SalesRepresentative("Dina", this.SalesRepresentativeQueue, this.customerManagerQueue, this.cashierQueue, this.stock);
		SalesRepresentative sale2 = new SalesRepresentative("Yael", this.SalesRepresentativeQueue, this.customerManagerQueue, this.cashierQueue, this.stock);
		
		this.employess.add(sale1);
		this.employess.add(sale2);
		this.employeesThreads.add(new Thread(sale1));
		this.employeesThreads.add(new Thread(sale2));
				
	}
	
	private void createCustomerManagersThreads(int numOfManagers) {
		
		for(int i=0; i<numOfManagers; i++) {
			//int randExpectedCustomers = (int) (5+ 45*Math.random()); //num of expected customers arrive to store can be between 5-50
			CustomerManager m = new CustomerManager("Customer manager number" + i+1,this.numOfExpectedCustomers,this.employess, this.storeQueues);
			this.employeesThreads.add(new Thread(m));
			this.employess.add(m);
		}
		
	}
	
	private void createCashierThreads() {
		
		Cashier cash = new Cashier("Itai", this.cashierQueue, this.customerManagerQueue, this.employess);
		this.employeesThreads.add(new Thread(cash));
		this.employess.add(cash);
	}
	
	//start all employees' threads
	private void startAll() {
		
		for(int i = 0; i<this.employeesThreads.size(); i++) {
			this.employeesThreads.elementAt(i).start();
		}
	}
	
	//read from file and create customers accordingly 
	private int reciveCustomerToStore(String customerFile)throws IOException, InterruptedException {
		
		Vector<String[]> customerData;
		customerData = FileReader(customerFile); // creating vector of customers data

		// extracting the data into the suitable fields starting from the first row
		for (int i = 1; i < customerData.size(); i++) {
			
			String name = customerData.elementAt(i)[0];
			String customerType = customerData.elementAt(i)[1];
			int arrivalTime = Integer.parseInt(customerData.elementAt(i)[2]);
			String ElectricVehicalType = customerData.elementAt(i)[3];
			boolean ElectricVehical=false;
			
			if(ElectricVehicalType.equals("Scooter")) {
				ElectricVehical = false;
			}
			
			else if(ElectricVehicalType.equals("Bike")) {
				ElectricVehical = true;
			}

			if(customerType.contentEquals("purchesing")) {
				buyCustomer c1= new buyCustomer (name, arrivalTime, ElectricVehical, this.clercksQueue);
			}
			
			//if type is repairment and customer has ElecticVehicle - create new ElecticVehicle and new TechnicalCustomer
			if(customerType.contentEquals("repairment")) {
				
				String model = customerData.elementAt(i)[4];
				int maxSpeed = Integer.parseInt(customerData.elementAt(i)[5]);
				int weight = Integer.parseInt(customerData.elementAt(i)[6]);
				Boolean isClothing = Boolean.parseBoolean(customerData.elementAt(i)[7]);
				
				TechnicalCustomer c2 =  new TechnicalCustomer(name, arrivalTime,ElectricVehical,this.clercksQueue, model, maxSpeed, weight, isClothing);
			}
		}
		
		return customerData.size()-1; //num of total fields is the num of total customers arrived to store
	}
	
	private Vector<ElectricVehicle> CreateStockVector(String stockFile) throws IOException {

		stock = new Vector<ElectricVehicle>();
		Vector<String[]> stockData;
		stockData = FileReader(stockFile); // creating vector of ElectricVehicle data

		// extracting the data into the suitable fields starting from the first row
		for (int i = 1; i < stockData.size(); i++) {
			String VehicleType = stockData.elementAt(i)[0];
			String model = stockData.elementAt(i)[1];
			int maxSpeed =  Integer.parseInt(stockData.elementAt(i)[2]);
			int weight = Integer.parseInt(stockData.elementAt(i)[3]);
			Boolean isClothing = Boolean.parseBoolean(stockData.elementAt(i)[4]);
			double price = Double.parseDouble(stockData.elementAt(i)[5]);
			int unitsInStock = Integer.parseInt(stockData.elementAt(i)[6]);

			
			if(VehicleType.equals("Scooter")) {
				Scooter s = new Scooter (model,maxSpeed,weight,price,unitsInStock);
				stock.add(s);
			}
			else if (VehicleType.equals("Bike")) {
				
				Bike b = new Bike (model, maxSpeed, weight,price,unitsInStock, isClothing);
				stock.add(b);
			}
		}
		return stock;
	}
	
	// reads from file and creates vector of string arrays. each array represent one record (line) in the file
		public static Vector<String[]> FileReader(String file_name) throws IOException {
			Vector<String[]> data = new Vector<String[]>();
			BufferedReader inFile = null;
			try {
				FileReader fr = new FileReader(file_name);
				inFile = new BufferedReader(fr);
				String newLine;

				while ((newLine = inFile.readLine()) != null) {

					String[] seperateLine = newLine.split("\t"); // split line into array of different strings which separate by tab
					data.add(seperateLine); // add the array to the data vector
				}
			}

			// FileNotFoundException
			catch (FileNotFoundException exception) {
				System.out.println("The file " + file_name + " was not found.");
			} catch (IOException exception) {
				System.out.println(exception);
			} finally {
				inFile.close();
			}
			return data;
		}

	

}
