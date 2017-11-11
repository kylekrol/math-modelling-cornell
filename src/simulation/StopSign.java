package simulation;

/**
 * Represents a stop sign element in a bus route. A stop sign element will
 * cause the bus to come to a complete stop and idle for an expected wait
 * time specific to this stop sign.
 */
public class StopSign implements Element {

	/** Expected wait time in seconds */
	private double wait;

	/** Creates a new stop sign with the specified wait time */
	StopSign(double wait) {
		this.wait = wait;
	}

	@Override
	public void drive(Bus bus) {
		bus.brake(0.0d);
		bus.idle(wait);
	}
}
