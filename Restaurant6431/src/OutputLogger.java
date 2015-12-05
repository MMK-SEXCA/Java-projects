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
	private DinerEntry[] outputData;
	private ArrayList<DinerEntry> DinerLogs;
	public String header = "Diner\t" +"Arrived" + "Seating\t" +"Table-ID" +"Cook-ID\t" +"Food\t" + 
			"Leaving\t"+"BTime\t"+"FTime\t"+"CTime\t"+"STime";
	
	private OutputLogger() {
		
	}
	
	/**
	 * @param numberOfDiners
	 */
	public void initialize(int numberOfDiners) {
		outputData = new DinerEntry[numberOfDiners];
		for(int i=0; i<outputData.length; i++) {
			outputData[i] = new DinerEntry();
		}
		DinerLogs = new ArrayList<DinerEntry>();
		for(int i=0; i<outputData.length; i++) {
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