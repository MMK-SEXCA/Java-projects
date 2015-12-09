package Resources;
/**
 * 
 */

/**
 * @author Rakshith Kunchum
 *
 */
public class Clock {
	
	private static Clock clock = null;
	private static int time;
	
	/**
	 * default constructor
	 */
	public Clock() {
		time = 0;
	}
	
	public int getTime() {
		return time;
	}

	/**
	 * @return
	 */
	public static Clock getStaticInstance() {
		if(clock == null) {
			clock = new Clock();
		}
		return clock;
	}
	
	public static void increment() {
		try {
			Thread.sleep(50);
		} catch(InterruptedException ie) {
			
		}
		time++;
	}
}
