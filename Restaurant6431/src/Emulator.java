/**
 * Emulator class to emulate the running of the restaurant.
 */

/**
 * @author Rakshith Kunchum
 *
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Emulator {
	
	private final int MAX_TIME = Constants.getMaxTime();
	private Customers customers;
	private Cooks cooks;
	private Tables tables;
	private Clock clock;
	private Logger logger;

	public static void main(String[] args) {
		if (args.length < 1) {
			System.out.println("Input file Not Found/Input file name argument is missing.\nPlease run the project in the following format : " +
					"java Emulator <InputFile name>");
			System.exit(0);
		}
		Emulator restaurant = parseInput(args[0]);
		restaurant.runSimulation();
		restaurant.printOutput();
	}

	/**
	 * @param string
	 */
	private static Emulator parseInput(String inputFilename) {
		Emulator restaurant = null;
		try{
			FileReader inputFile = new FileReader(inputFilename);
			BufferedReader inputReader = new BufferedReader(inputFile);
			restaurant = new Emulator(Integer.parseInt(inputReader.readLine().trim()),
					Integer.parseInt(inputReader.readLine().trim()), Integer.parseInt(inputReader.readLine().trim()));
			String line;
			int orderNumber = 0;
			while((line = inputReader.readLine()) != null){
				String orderLine[] = line.split("\\s+");
				int arrivalTimeStamp = Integer.parseInt(orderLine[0].trim());
				Order newDinerOrder = new Order(Integer.parseInt(orderLine[1].trim()), 
						Integer.parseInt(orderLine[2].trim()), Integer.parseInt(orderLine[3].trim()), Integer.parseInt(orderLine[4].trim()));
				restaurant.customers.addDiner(orderNumber, new Customer(arrivalTimeStamp,newDinerOrder,orderNumber));
				orderNumber++;
			}
			inputReader.close();
		}catch(Exception ex){
			System.out.println("Issue with opening/reading the input file.");
		}
		return restaurant;
	}

	private void runSimulation() {
		while(clock.getTime() <= MAX_TIME || Customers.getStaticInstance().getNumberOfCurrentDiners() > 0) {
			customers.startDinersArrivedNow();
			clock.increment(); 
			synchronized(clock) {
				clock.notifyAll();
			}
		}
	}
	
	/**
	 * return null
	 */
	private void printOutput() {
		String str;
		str = logger.header;
		System.out.println(str);
		ArrayList<CustomerEntry> entry = logger.getOutputData();
		for(int i=0; i<entry.size(); i++) {
			str = 	i + "\t" + entry.get(i).toString();
			System.out.println(str);
		}
	}
	
	/**
	 * @param numberOfDiners
	 * @param numberOfCooks
	 * @param numberOfTables
	 */
	public Emulator(int numberOfDiners, int numberOfTables, int numberOfCooks) {
		clock = Clock.getStaticInstance();
		tables = Tables.getStaticInstance();
		tables.initialize(numberOfTables);
		customers = Customers.getStaticInstance();
		customers.initialize(numberOfDiners);
		cooks = Cooks.getStaticInstance();
		cooks.initialize(numberOfCooks);
		logger = Logger.getStaticInstance();
		logger.initialize(numberOfDiners);
	}
}
