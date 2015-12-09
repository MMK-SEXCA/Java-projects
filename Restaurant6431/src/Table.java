/**
 * 
 */

/**
 * @author Rakshith Kunchum
 *
 */
public class Table {
	
	public int tableId;		
	public boolean isOccupied;
	public boolean cookAssigned;
	public boolean foodServed;
	private Order order;
	public Cook cook;
	public Customer customer;
	public int timeBurgerMacihineWasUsed;
	public int timeFriesMachineWasUsed;
	public int timeSodaMachineWasUsed;
	public int timeSundaeMachineWasUsed;
	public int timeFoodBroughtToTable;
	
	/**
	 * @param tableId
	 */
	public Table(int tableId) {
		this.tableId = tableId;
		this.isOccupied = false;
		this.cookAssigned = false;
		this.foodServed = false;
		this.order = null;
	}
	
	public void release() {
		this.isOccupied = false;
		this.cookAssigned = false;
		this.foodServed = false;
		this.order = null;
	}
	
	public Order getOrder() {
		return order;
	}

	public synchronized void setOrder(Order order) {
		this.order = order;
		notifyAll();
	}
	
	public synchronized void waitOnCookAssigned() {
		try {
			while(!this.cookAssigned)
				wait();
		} catch(InterruptedException ie) {}
	}
	
	public synchronized void assignCook(Cook cook) {
		this.cookAssigned = true;
		this.cook = cook;
		notifyAll();
	}
	
	public synchronized void waitOnOrder() {
		try {
			while(this.order == null)
				wait();
		} catch(InterruptedException ie) {}
	}
	
	public synchronized void waitOnFoodServed() {
		try {
			while(!this.foodServed)
				wait();
		} catch(InterruptedException ie) {}
	}
	
	public synchronized void serveFood() {
		this.foodServed = true;
		this.timeFoodBroughtToTable = Clock.getStaticInstance().getTime();
		notifyAll();
	}
}
