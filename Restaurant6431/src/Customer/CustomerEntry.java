package Customer;
/**
 * 
 */

/**
 * @author Rakshith Kunchum
 *
 */
public class CustomerEntry {
	
	public int arrivalTime;
	public int seatingTime;
	public int tableNumber;
	public int cookNumber;
	public int burgerMachineUsedTime;
	public int friesMachineUsedTime;
	public int sodaMachineUsedTime;
	public int sundaeMachineUsedTime;
	public int foodServedTime;
	public int timeOfLeaving;
	
	public String toString(){
		return arrivalTime + "\t" + seatingTime + "\t" + tableNumber + "\t" + cookNumber + "\t" + 
				foodServedTime + "\t" + timeOfLeaving+ "\t" + burgerMachineUsedTime+ "\t" + friesMachineUsedTime+ "\t" +
				sodaMachineUsedTime+ "\t" + sundaeMachineUsedTime;
	}
}