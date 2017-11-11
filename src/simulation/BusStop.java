package simulation;

import simulation.buses.Bus;

/**
 * Represents a bus stop element in a bus route. A bus stop element will
 * cause the bus to come to a complete stop and idle for an expected wait
 * time specific to this bus stop.
 */
public class BusStop extends WaitElement {

	/** Creates a new bus stop with the specified congestion factor */
	BusStop(double congestion) {
		super(congestion);
	}
	
	@Override
	public void drive(Bus bus) {
		bus.brake(0.0d);
		// bus.idle(); // TODO
	}
}
