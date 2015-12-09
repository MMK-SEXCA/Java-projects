package Machines;
/**
 * 
 */
import Resources.Clock;
import Resources.Cook;
import Customer.Order;
/**
 * @author Rakshith Kunchum
 *
 */
public class DispenseFoodItem {
	public int timer;
	public boolean isOccupied;
	
	public DispenseFoodItem(int timer) {
		this.timer = timer;
		isOccupied = false;
    }

	synchronized public void occupy() {
		isOccupied = true;
	}
	
	synchronized public boolean isOccupied() {
		return isOccupied;
	}
	
	public void prepare(Order order, Cook cook) {
		int timeForCooking = timer;
		if(this instanceof BurgerDispenser){
			timeForCooking = order.numberOfBurgers * timer;
		}else if(this instanceof FriesDispenser){
			timeForCooking = order.numberOfFries * timer;
		}else if(this instanceof CokeDispenser){
			timeForCooking = order.numberOfCokes * timer;
		}else if(this instanceof SundaeDispenser){
			timeForCooking = order.numberOfSundae * timer;
		}
		
		int startTime = Clock.getStaticInstance().getTime();
		if(this instanceof BurgerDispenser){
			cook.timeBurgerMacihineWasUsed = startTime;
		}else if(this instanceof FriesDispenser){
			cook.timeFriesMachineWasUsed = startTime;
		}else if(this instanceof CokeDispenser){
			cook.timeSodaMachineWasUsed = startTime;
		}else if(this instanceof SundaeDispenser){
			cook.timeSundaeMachineWasUsed = startTime;
		}
		
		while(Clock.getStaticInstance().getTime() < startTime + timeForCooking) {
			//cooking
			try {
				synchronized(Clock.getStaticInstance()) {
					Clock.getStaticInstance().wait();
				}
			} catch(InterruptedException ie) {}
		}
		this.isOccupied = false;
		
		if(this instanceof BurgerDispenser){
			order.burgersReady = true;
			//System.out.println("Time : "+Timer.getStaticInstance().getTime()+" Burgers are ready by "+Thread.currentThread().getName());
		}else if(this instanceof FriesDispenser){
			order.friesReady = true;
			//System.out.println("Time : "+Timer.getStaticInstance().getTime()+" Fries are ready by "+Thread.currentThread().getName());
		}else if(this instanceof CokeDispenser){
			order.cokeReady = true;
			//System.out.println("Time : "+Timer.getStaticInstance().getTime()+" Coke is ready by "+Thread.currentThread());
		}else if(this instanceof SundaeDispenser){
			order.sundaeReady = true;
			//System.out.println("Time : "+Timer.getStaticInstance().getTime()+" Sundae is ready by "+Thread.currentThread().getName());
		}
	}
}
