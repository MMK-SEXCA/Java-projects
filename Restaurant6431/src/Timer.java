/**
 * 
 */

/**
 * @author Jhansi
 *
 */
public class Timer {
	
	private static Timer timer = null;
	private static int time;
	
	/**
	 * default constructor
	 */
	public Timer() {
		time = 0;
	}
	
	public static int getTime() {
		return time;
	}

	/**
	 * @return
	 */
	public static Timer getStaticInstance() {
		if(timer == null) {
			timer = new Timer();
		}
		return timer;
	}
	
	public static void increment() {
		try {
			Thread.sleep(50);
		} catch(InterruptedException ie) {
			
		}
		time++;
	}
}
