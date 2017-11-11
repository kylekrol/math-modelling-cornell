package simulation;

import java.util.Random;

import simulation.buses.Bus;

public class StopLight extends WaitElement {
	
	/** Random number generator object for this class */
	private static final Random rand = new Random();
	
	/** Speed the bus travels through the stop light */
	private double throughSpeed;

	/**
	 * Creates a new stop light with the specified congestion factor and
	 * through speed. The congestion factor will scale the standard stop light
	 * wait time to give this particular light a custom wait time. The through
	 * speed represents how fast a bus will cross this light if the light is
	 * green when the bus approaches.
	 * 
	 * @param congestion
	 * 		the congestion factor to scale the standard wait time
	 * @param throughSpeed
	 * 		the through speed of this light
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
