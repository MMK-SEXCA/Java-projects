/**
 * 
 */

/**
 * @author Rakshith Kunchum
 *
 */
public class VendingMachine {
	private final int MAX_TIME=120;
	private static VendingMachine instance = null;
	private BurgerDispenser burgerDispenser;
	private FriesDispenser friesDispenser;
	private CokeDispenser cokeDispenser;
	private SundaeDispenser sundaeDispenser;
	
	private VendingMachine() {
		burgerDispenser = new BurgerDispenser(5);
		friesDispenser = new FriesDispenser(3);
		cokeDispenser  = new CokeDispenser(2);
		sundaeDispenser = new SundaeDispenser(1);
	}
	
	public static VendingMachine getInstance() {
		if(instance == null) {
			instance = new VendingMachine();
		}
		return instance;
	}
	
	public DispenseFoodItem getMachineFor(Order order) {
		while(Clock.getStaticInstance().getTime() <=MAX_TIME || Customers.getStaticInstance().getNumberOfCurrentDiners() > 0) {
			if(!order.burgersReady) {
				if(!burgerDispenser.isOccupied()) {
					synchronized(burgerDispenser) {
						if(!burgerDispenser.isOccupied()) {		
							burgerDispenser.occupy();
							//System.out.println("Time : "+Timer.getStaticInstance().getTime()+" Burger machine being alloted to : "+Thread.currentThread().getName());
							return burgerDispenser;
						}
					}
				}
			}
			if(!order.friesReady) {
				if(!friesDispenser.isOccupied()) {
					synchronized(friesDispenser) {
						if(!friesDispenser.isOccupied()) {	
							friesDispenser.occupy();
							//System.out.println("Time : "+Timer.getStaticInstance().getTime()+" Fries machine being alloted to : "+Thread.currentThread().getName());
							return friesDispenser;
						}
					}
				}
			}
			if(!order.cokeReady) {
				if(!cokeDispenser.isOccupied()) {
					synchronized(cokeDispenser) {
						if(!cokeDispenser.isOccupied()) {	
							cokeDispenser.occupy();
							//System.out.println("Time : "+Timer.getStaticInstance().getTime()+" Coke machine being alloted to : "+Thread.currentThread().getName());
							return cokeDispenser;
						}
					}
				}
			}
			if(!order.sundaeReady) {
				if(!sundaeDispenser.isOccupied()) {
					synchronized(sundaeDispenser) {
						if(!sundaeDispenser.isOccupied()) {	
							sundaeDispenser.occupy();
							//System.out.println("Time : "+Timer.getStaticInstance().getTime()+" Sundae machine being alloted to : "+Thread.currentThread().getName());
							return sundaeDispenser;
						}
					}
				}
			}
		}	
		return null;
	}
}
