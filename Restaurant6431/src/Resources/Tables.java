package Resources;
/**
 * 
 */
import Customer.Customers;
import Customer.Customer;
/**
 * @author Rakshith Kunchum
 *
 */
public class Tables {
	
	private Table[] tables;
	private static Tables instance = null;
	
	/**
	 * @return Tables
	 */
	public static Tables getStaticInstance() {
		// TODO Auto-generated method stub
		if (instance == null)
		{
			synchronized(Tables.class) {
				if(instance == null)
					instance = new Tables();
		    }
		}
		return instance;
	}

	/**
	 * @param numberOfTables
	 */
	public void initialize(int numberOfTables) {
		// TODO Auto-generated method stub
		if(tables == null) {
			tables = new Table[numberOfTables];
			for(int i=0; i<tables.length; i++) {
				tables[i] = new Table(i);
			}
		}
	}

	/**
	 * @param customer
	 * @return
	 */
	synchronized public Table getTableForDiner(Customer customer) {
		// TODO Auto-generated method stub
		int index = -1;
		while(index == -1) {
			if(Customers.getStaticInstance().isEarliest(customer.getDinerId())) {
				for(int i=0; i<tables.length; i++) {
					if(!tables[i].isOccupied) {
						index = i;
						//System.out.println("Time : "+Timer.getStaticInstance().getTime()+" Table-"+index+" is being allotted to "+Thread.currentThread().getName());
						break;
					}
				}
			}
			try {
				if(index == -1)
					wait();
			} catch(InterruptedException ie) {
				System.out.println(ie.getMessage());
			}
		}
		tables[index].isOccupied = true;
		tables[index].customer = customer;
		notifyAll();
		return tables[index];
	}
	
	synchronized public Table getTableForCook() {
		int index = -1;
		while(index == -1) {
			for(int i=0; i<tables.length; i++) {
				if(tables[i].isOccupied && !tables[i].cookAssigned) {
					index = i;
					//TODO : Implement First Come First Serve order.
				}
			}
			
			if(index == -1) {
				if(Customers.getStaticInstance().getNumberOfCurrentDiners() == 0)
					break;
				try {
					wait();
				} catch(InterruptedException ie) {
					System.out.println(ie.getMessage());
				}
			}
		}
		if(index == -1)
			return null;
		tables[index].cookAssigned = true;
		//System.out.println("Time : "+Timer.getStaticInstance().getTime()+" cook assigned to table-"+index+" ;served by "+Thread.currentThread().getName());
		notifyAll();
		return tables[index]; 
	}
	
	synchronized public void releaseTable(int index) {
		System.out.println("Time : "+Clock.getStaticInstance().getTime()+"\t"+Thread.currentThread().getName()+" is leaving.");
		tables[index].release();
	}
}
