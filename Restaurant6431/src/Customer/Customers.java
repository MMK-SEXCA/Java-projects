package Customer;
/**
 * 
 */
import Resources.*;
/**
 * @author Rakshith Kunchum
 *
 */
public class Customers {
	
	private static Customers instance = null;
	private Customer[] customers;
	private int numberOfCurrentDiners;
	
	/**
	 * @param numberOfDiners
	 */
	public Customers() {
		this.numberOfCurrentDiners = 0;
	}

	/**
	 * @param numberOfDiners
	 */
	public void initialize(int numberOfDiners) {
		customers = new Customer[numberOfDiners];
	}

	/**
	 * @return
	 */
	public static Customers getStaticInstance() {
		if(instance == null) {
			instance = new Customers();
		}
		return instance;
	}
	
	public int getNumberOfCurrentDiners() {
		return this.numberOfCurrentDiners;
	}
	
	public int getNumberOfDiners() {
		return customers.length;
	}
	
	public Customer getDiner(int index) {
		if(index < customers.length)
			return customers[index];
		else
			return null;
	}
	
	public void addDiner(int index, Customer customer) {
		customers[index] = customer;
	}
	
	public void startDinersArrivedNow() {
		for(int i=0; i<customers.length; i++) {
			if(customers[i].getArrivalTime() == Clock.getStaticInstance().getTime()) {
				customers[i].dinerEnterRestaurant();
				System.out.println("Time : "+Clock.getStaticInstance().getTime()+"\t"+"Customer ID - " + i + " arrived.");
				this.numberOfCurrentDiners++;
			}
		}
	}
	
	public void leaveRestaurant() {
		this.numberOfCurrentDiners--;
	}
	
	public boolean isEarliest(int dinerId) {
		for(int i=0; i<customers.length; i++) {
			if(customers[i].isInRestaurant() && customers[i].getSeatingTime() == -1) {
				if(dinerId == i)
					return true;
				else
					return false;
			}
		}
		return false;
	}
}
