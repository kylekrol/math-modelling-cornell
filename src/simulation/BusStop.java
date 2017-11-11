package simulation;

/**
 * Represents a bus stop element in a bus route. A bus stop element will
 * cause the bus to come to a complete stop and idle for an expected wait
 * time specific to this bus stop.
 */
public class BusStop implements Element {
	
	/** Expected wait time in seconds */
	private double wait;

	/** Creates a new bus stop with a specified wait time */
	BusStop(double wait) {
		this.wait = wait;
	}
	
	@Override
	public void drive(Bus bus) {
		bus.brake(0.0d);
		bus.idle(wait);
	}
}
