package simulation;

import simulation.buses.Bus;

/**
 * Represents a stop sign element in a bus route. A stop sign element will
 * cause the bus to come to a complete stop and idle for an expected wait
 * time specific to this stop sign.
 */
public class StopSign extends WaitElement {

	/** Creates a new stop sign with the specified congestion factor */
	StopSign(double congestion) {
		super(congestion);
	}

	@Override
	public void drive(Bus bus) {
		bus.brake(0.0d);
		//bus.idle(); // TODO
	}
}
