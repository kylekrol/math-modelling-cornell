package simulation;

import java.util.Random;

import simulation.buses.Bus;

/**
 * 
 */
public class StopLight extends WaitElement {
	
	/** Random number generator object for this class */
	private static final Random rand = new Random();
	
	/** Speed the bus travels through the stop light */
	private double throughSpeed;

	/**
	 * Creates a new stop light with the specified congestion and through
	 * speed.
	 */
	StopLight(double congestion, double throughSpeed) {
		super(congestion);
		this.throughSpeed = throughSpeed;
	}
	
	@Override
	public void drive(Bus bus) {
		// TODO Auto-generated method stub
		
	}
}
