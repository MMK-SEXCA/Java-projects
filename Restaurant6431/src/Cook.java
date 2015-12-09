/**
 * 
 */

/**
 * @author Rakshith Kunchum
 *
 */
public class Cook implements Runnable{
	
	private final int MAX_TIME = Constants.getMaxTime();
	private int cookId;		
	private Table tableServing;
	private Order order;
	private Thread thread;
	public int timeBurgerMacihineWasUsed;
	public int timeFriesMachineWasUsed;
	public int timeSodaMachineWasUsed;
	public int timeSundaeMachineWasUsed;
	
	public Cook(int cookId) {
		this.cookId = cookId;
		this.thread = new Thread(this, "Cook ID - "+this.cookId);
		this.thread.start();
	}
	
	public int getId() {
		return this.cookId;
	}
	
	public void run() {
		while(Clock.getStaticInstance().getTime() <= MAX_TIME || Customers.getStaticInstance().getNumberOfCurrentDiners() > 0) {
				tableServing = Tables.getStaticInstance().getTableForCook();
				if(tableServing != null) {
					InitailizeMachineTimeUsed();
					this.tableServing.assignCook(this);
					this.tableServing.waitOnOrder();
					order = this.tableServing.getOrder();
					VendingMachine machine = VendingMachine.getInstance();
					while(!order.isComplete()) {
						DispenseFoodItem fooditem = machine.getMachineFor(order);
						fooditem.prepare(order, this);
					}
					CopyMachineTimeUsed();
					this.tableServing.serveFood();
				}
		}
	}

	/**
	 * 
	 */
	private void CopyMachineTimeUsed() {
		this.tableServing.timeBurgerMacihineWasUsed = this.timeBurgerMacihineWasUsed;
		this.tableServing.timeFriesMachineWasUsed = this.timeFriesMachineWasUsed;
		this.tableServing.timeSodaMachineWasUsed = this.timeSodaMachineWasUsed;
		this.tableServing.timeSundaeMachineWasUsed = this.timeSundaeMachineWasUsed;
	}

	/**
	 * 
	 */
	private void InitailizeMachineTimeUsed() {
		this.timeFriesMachineWasUsed = -1;
		this.timeSodaMachineWasUsed = -1;
		this.timeSundaeMachineWasUsed = -1;
	}
}
