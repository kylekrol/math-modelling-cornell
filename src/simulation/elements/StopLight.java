package simulation.elements;

import console.Constants;

/**
 * Represents a stop light element in a bus route. A stop light element will
 * cause the bus to undergo one of two actions. If the light is green, the bus
 * will slow to the through speed and pass through the light. If the light is
 * not green, the bus will stop completely and wait a specified amount of time.
 */
public class StopLight extends WaitElement {

	/**
	 * Boolean describing the light's state. This variable is refreshed
	 * whenever hasEffect() is called
	 */
	private boolean isGreen;
	/** Whether or not the bus is turning at this light */
	private boolean turn;

	/**
	 * Creates a new stop light with the specified wait time and the turning
	 * flag value.
	 */
	public StopLight(double wait, boolean turn) {
		super(wait);
		this.turn = turn;
	}
	
	@Override
	public boolean hasWait() {
		return (WaitElement.rand.nextDouble() < Constants.LIGHT_THROUGH_CHANCE);
	}
	
	@Override
	public boolean hasDeltaV() {
		isGreen = (WaitElement.rand.nextDouble() < Constants.LIGHT_THROUGH_CHANCE);
		return !(isGreen && !turn);
	}
	
	@Override
	public double getVelocity() {
		return (isGreen ? Constants.TURN_SPEED : 0.0d);
	}
}
