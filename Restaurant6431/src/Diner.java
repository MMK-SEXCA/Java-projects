/**
 * 
 */

/**
 * @author Jhansi
 *
 */
public class Diner implements Runnable{
	
	private final int EATING_TIME = 30; 
	private int dinerId;
	private int arrivalTime;		
	private int seatingTime;
	private int servedTime;
	private Table seatedTable;	
	private DinerOrder order;			 
	private Cook cook;
	private boolean inRestaurant;
	private Thread thread;			
	
	/**
	 * @param arrivalTimeStamp
	 * @param newDinerOrder
	 * @param orderNumber
	 */
	public Diner(int arrivalTimeStamp, DinerOrder newDinerOrder, int orderNumber) {
		this.dinerId = orderNumber;
		this.arrivalTime = arrivalTimeStamp;
		this.seatingTime = -1;
		this.order = newDinerOrder;
		this.inRestaurant = false;
		this.thread = new Thread(this, "Diner Number - "+this.dinerId);
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

	public DinerOrder getOrder() {
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
		OutputLogger output = OutputLogger.getStaticInstance();
		DinerEntry dinerEntry = output.getOutputData().get(dinerId);
		getTableForDiner();
		System.out.println("Time : "+Timer.getStaticInstance().getTime()+"\t"+Thread.currentThread().getName() + " is seated on Table-" + seatedTable.tableId);
		this.seatedTable.setOrder(this.order);
		this.seatedTable.waitOnCookAssigned();
		this.cook = this.seatedTable.cook;
		//To make sure cook is not accessed before being assigned.
		//while(cook==null){;}
		//System.out.println("Cook - " + cook.getId());
		dinerEntry.cookNumber = cook.getId();
		this.seatedTable.waitOnFoodServed();
		CopyTimeLogsToDinerEntry(dinerEntry);
		this.servedTime = Timer.getStaticInstance().getTime();
		System.out.println("Time : "+Timer.getStaticInstance().getTime()+"\t"+Thread.currentThread().getName() + " Started Eating");
		while (Timer.getStaticInstance().getTime() < servedTime + EATING_TIME) {
			try {
				synchronized(Timer.getStaticInstance()) {
					Timer.getStaticInstance().wait();
				}
			} catch(InterruptedException ie) {}
		}
		copyUsedTimeToDinerEntry(dinerEntry);
		Tables.getStaticInstance().releaseTable(seatedTable.tableId);
		dinerEntry.timeOfLeaving = Timer.getStaticInstance().getTime();
		leave();
	}
	
	/**
	 * 
	 */
	private void getTableForDiner() {
		this.seatedTable = Tables.getStaticInstance().getTableForDiner(this);
		this.seatingTime = Timer.getStaticInstance().getTime();
	}


	/**
	 * @param dinerEntry
	 */
	private void CopyTimeLogsToDinerEntry(DinerEntry dinerEntry) {
		dinerEntry.arrivalTime = this.arrivalTime;
		dinerEntry.seatingTime = this.seatingTime;
		dinerEntry.tableNumber = this.seatedTable.tableId;
	}

	/**
	 * @param dinerEntry
	 */
	private void copyUsedTimeToDinerEntry(DinerEntry dinerEntry) {
		dinerEntry.burgerMachineUsedTime = this.seatedTable.timeBurgerMacihineWasUsed;
		dinerEntry.friesMachineUsedTime = this.seatedTable.timeFriesMachineWasUsed;
		dinerEntry.sodaMachineUsedTime = this.seatedTable.timeSodaMachineWasUsed;
		dinerEntry.sundaeMachineUsedTime = this.seatedTable.timeSundaeMachineWasUsed;
		dinerEntry.foodServedTime = this.seatedTable.timeFoodBroughtToTable;
	}


	public void leave() {
		this.inRestaurant = false;
		synchronized(Diners.getStaticInstance()) {
			Diners.getStaticInstance().leaveRestaurant();}
		synchronized(Tables.getStaticInstance()) {
			Tables.getStaticInstance().notifyAll(); }
	}
}
