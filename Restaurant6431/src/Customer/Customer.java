package Customer;
/**
 * 
 */

import Resources.*;
import Constants.*;
import Output.Logger;
/**
 * @author Rakshith Kunchum
 *
 */
public class Customer implements Runnable{
	
	private final int EATING_TIME = Constants.getEatingTime(); 
	private int dinerId;
	private int arrivalTime;		
	private int seatingTime;
	private int servedTime;
	private Table seatedTable;	
	private Order order;			 
	private Cook cook;
	private boolean inRestaurant;
	private Thread thread;			
	
	/**
	 * @param arrivalTimeStamp
	 * @param newDinerOrder
	 * @param orderNumber
	 */
	public Customer(int arrivalTimeStamp, Order newDinerOrder, int orderNumber) {
		this.dinerId = orderNumber;
		this.arrivalTime = arrivalTimeStamp;
		this.seatingTime = -1;
		this.order = newDinerOrder;
		this.inRestaurant = false;
		this.thread = new Thread(this, "Customer ID - "+this.dinerId);
	}
	
	
	public int getDinerId() {
		return dinerId;
	}

	public int getArrivalTime() {
		return arrivalTime;
	}

	public int getSeatingTime() {
		return seatingTime;
	}

	public int getServedTime() {
		return servedTime;
	}

	public Order getOrder() {
		return order;
	}

	public boolean isInRestaurant() {
		return inRestaurant;
	}

	public void dinerEnterRestaurant() {
		this.inRestaurant = true;
		this.thread.start();
	}

	/* 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		Logger output = Logger.getStaticInstance();
		CustomerEntry customerEntry = output.getOutputData().get(dinerId);
		getTableForDiner();
		System.out.println("Time : "+Clock.getStaticInstance().getTime()+"\t"+Thread.currentThread().getName() + " is seated on Table - " + seatedTable.tableId);
		this.seatedTable.setOrder(this.order);
		this.seatedTable.waitOnCookAssigned();
		this.cook = this.seatedTable.cook;
		customerEntry.cookNumber = cook.getId();
		this.seatedTable.waitOnFoodServed();
		CopyTimeLogsToDinerEntry(customerEntry);
		this.servedTime = Clock.getStaticInstance().getTime();
		System.out.println("Time : "+Clock.getStaticInstance().getTime()+"\t"+Thread.currentThread().getName() + " Started Eating");
		while (Clock.getStaticInstance().getTime() < servedTime + EATING_TIME) {
			try {
				synchronized(Clock.getStaticInstance()) {
					Clock.getStaticInstance().wait();
				}
			} catch(InterruptedException ie) {}
		}
		copyUsedTimeToDinerEntry(customerEntry);
		Tables.getStaticInstance().releaseTable(seatedTable.tableId);
		customerEntry.timeOfLeaving = Clock.getStaticInstance().getTime();
		leave();
	}
	
	/**
	 * 
	 */
	private void getTableForDiner() {
		this.seatedTable = Tables.getStaticInstance().getTableForDiner(this);
		this.seatingTime = Clock.getStaticInstance().getTime();
	}


	/**
	 * @param customerEntry
	 */
	private void CopyTimeLogsToDinerEntry(CustomerEntry customerEntry) {
		customerEntry.arrivalTime = this.arrivalTime;
		customerEntry.seatingTime = this.seatingTime;
		customerEntry.tableNumber = this.seatedTable.tableId;
	}

	/**
	 * @param customerEntry
	 */
	private void copyUsedTimeToDinerEntry(CustomerEntry customerEntry) {
		customerEntry.burgerMachineUsedTime = this.seatedTable.timeBurgerMacihineWasUsed;
		customerEntry.friesMachineUsedTime = this.seatedTable.timeFriesMachineWasUsed;
		customerEntry.sodaMachineUsedTime = this.seatedTable.timeSodaMachineWasUsed;
		customerEntry.sundaeMachineUsedTime = this.seatedTable.timeSundaeMachineWasUsed;
		customerEntry.foodServedTime = this.seatedTable.timeFoodBroughtToTable;
	}


	public void leave() {
		this.inRestaurant = false;
		synchronized(Customers.getStaticInstance()) {
			Customers.getStaticInstance().leaveRestaurant();}
		synchronized(Tables.getStaticInstance()) {
			Tables.getStaticInstance().notifyAll(); }
	}
}
