import java.util.ArrayList;

/**
 * 
 */

/**
 * @author Jhansi
 *
 */
public class OutputLogger {
	private static OutputLogger instance;
	private ArrayList<DinerEntry> DinerLogs;
	public String header = "Diner\t" +"Arrived" + "Seating\t" +"Table-ID" +"Cook-ID\t" +"Food\t" + 
			"Leaving\t"+"BTime\t"+"FTime\t"+"CTime\t"+"STime";
	
	private OutputLogger() {
		
	}
	
	/**
	 * @param numberOfDiners
	 */
	public void initialize(int numberOfDiners) {
		DinerLogs = new ArrayList<DinerEntry>();
		for(int i=0; i<numberOfDiners; i++) {
			DinerLogs.add(new DinerEntry());
		}
	}
	
	/**
	 * @return
	 */
	public static OutputLogger getStaticInstance() {
		if(instance == null) {
			instance = new OutputLogger();
		}
		return instance;
	}
	
	/**
	 * @return
	 */
	public ArrayList<DinerEntry> getOutputData() {
		return DinerLogs;
	}
}