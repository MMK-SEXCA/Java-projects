package Output;
import Customer.*;

import java.util.ArrayList;

/**
 * 
 */

/**
 * @author Rakshith Kunchum
 *
 */
public class Logger {
	private static Logger instance;
	private ArrayList<CustomerEntry> DinerLogs;
	public String header = "Diner\t" +"Arrived" + "Seating\t" +"Table-ID" +"Cook-ID\t" +"Serving\t" + 
			"Leaving\t"+"Burger\t"+"Fries\t"+"Coke\t"+"Sundae";
	
	private Logger() {
		
	}
	
	/**
	 * @param numberOfDiners
	 */
	public void initialize(int numberOfDiners) {
		DinerLogs = new ArrayList<CustomerEntry>();
		for(int i=0; i<numberOfDiners; i++) {
			DinerLogs.add(new CustomerEntry());
		}
	}
	
	/**
	 * @return
	 */
	public static Logger getStaticInstance() {
		if(instance == null) {
			instance = new Logger();
		}
		return instance;
	}
	
	/**
	 * @return
	 */
	public ArrayList<CustomerEntry> getOutputData() {
		return DinerLogs;
	}
}