package simulation.elements;

/**
 * Represents a bus stop element in a bus route. A bus stop element will cause
 * the bus to come to a complete stop and idle for a specified amount of time.
 */
public class BusStop extends WaitElement {

	/** Creates a new bus stop with the specified wait times */
	public BusStop(double wait) {
		super(wait);
	}
	
	@Override
	public boolean hasWait() {
		return true;
	}

	@Override
	public boolean hasDeltaV() {
		return true;
	}

	@Override
	public double getVelocity() {
		return 0.0d;
	}
}
