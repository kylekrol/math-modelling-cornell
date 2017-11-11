package simulation.elements;

import console.Constants;
import simulation.WaitElement;
import simulation.buses.Bus;

/**
 * Represents a stop light element in a bus route. A stop light element will
 * cause the bus to undergo one of two actions. If the light is green, the bus
 * will slow to the through speed and pass through the light. If the light is
 * not green, the bus will stop completely and wait a specified amount of time.
 */
public class StopLight extends WaitElement {

	/** Speed the bus travels through the stop light */
	private double throughSpeed;

	/**
	 * Creates a new stop light with the specified wait time and through
	 * speed.
	 */
	public StopLight(double wait, double throughSpeed) {
		super(wait);
		this.throughSpeed = throughSpeed;
	}

	@Override
	public void drive(Bus bus) {
		if(WaitElement.rand.nextDouble() < Constants.LIGHT_THROUGH_CHANCE) {
			bus.brake(throughSpeed);
		} else {
			bus.brake(0.0d);
			bus.idle(wait);
		}
	}
}
