package simulation.elements;

import console.Constants;
import simulation.buses.Bus;

/**
 * Represents a stop sign element in a bus route. A stop sign element will
 * cause the bus to come to a complete stop and idle for an expected wait
 * time specific to this stop sign.
 */
public class StopSign extends WaitElement {

	/** Creates a new stop sign with the specified wait time */
	public StopSign(double wait) {
		super(wait);
	}

	@Override
	public void drive(Bus bus) {
		bus.brake(0.0d);
		bus.idle(super.wait);
	}
}
