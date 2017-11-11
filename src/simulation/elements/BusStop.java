package simulation.elements;

import simulation.WaitElement;
import simulation.buses.Bus;

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
	public void drive(Bus bus) {
		bus.brake(0.0d);
		bus.idle(super.wait);
	}
}
