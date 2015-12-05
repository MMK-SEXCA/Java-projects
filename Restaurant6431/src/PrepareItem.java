/**
 * 
 */

/**
 * @author Jhansi
 *
 */
public class PrepareItem {
	public int timer;
	public boolean isOccupied;
	private String machine;
	
	public PrepareItem(String name) {
		this.machine = name;
		isOccupied = false;
    }
	
	public String getName() {
		return machine;
	}
	
	synchronized public void occupy() {
		isOccupied = true;
	}
	
	synchronized public boolean isOccupied() {
		return isOccupied;
	}
	
	public void prepare(DinerOrder order, Cook cook) {
		int timeForCooking = timer;
		if(this instanceof BurgerMachine){
			timeForCooking = order.numberOfBurgers * timer;
		}else if(this instanceof FriesMachine){
			timeForCooking = order.numberOfFries * timer;
		}else if(this instanceof CokeMachine){
			timeForCooking = order.numberOfCokes * timer;
		}else if(this instanceof SundaeMachine){
			timeForCooking = order.numberOfSundae * timer;
		}
		
		int startTime = Timer.getStaticInstance().getTime();
		if(this instanceof BurgerMachine){
			cook.timeBurgerMacihineWasUsed = startTime;
		}else if(this instanceof FriesMachine){
			cook.timeFriesMachineWasUsed = startTime;
		}else if(this instanceof CokeMachine){
			cook.timeSodaMachineWasUsed = startTime;
		}else if(this instanceof SundaeMachine){
			cook.timeSundaeMachineWasUsed = startTime;
		}
		
		while(Timer.getStaticInstance().getTime() < startTime + timeForCooking) {
			//cooking
			try {
				synchronized(Timer.getStaticInstance()) {
					Timer.getStaticInstance().wait();
				}
			} catch(InterruptedException ie) {}
		}
		this.isOccupied = false;
		
		if(this instanceof BurgerMachine){
			order.burgersReady = true;
			//System.out.println("Time : "+Timer.getStaticInstance().getTime()+" Burgers are ready by "+Thread.currentThread().getName());
		}else if(this instanceof FriesMachine){
			order.friesReady = true;
			//System.out.println("Time : "+Timer.getStaticInstance().getTime()+" Fries are ready by "+Thread.currentThread().getName());
		}else if(this instanceof CokeMachine){
			order.cokeReady = true;
			//System.out.println("Time : "+Timer.getStaticInstance().getTime()+" Coke is ready by "+Thread.currentThread());
		}else if(this instanceof SundaeMachine){
			order.sundaeReady = true;
			//System.out.println("Time : "+Timer.getStaticInstance().getTime()+" Sundae is ready by "+Thread.currentThread().getName());
		}
	}
}
