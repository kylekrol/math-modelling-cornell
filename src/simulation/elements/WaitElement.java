package simulation.elements;

import java.util.Random;

import simulation.buses.Bus;

/**
 * Represents any element that has the potential to cause the bus to idle
 * during a route.
 */
public abstract class WaitElement {
	
	/** Random number generator object for wait elements */
	protected static final Random rand = new Random();

	/** Expected wait time for this element */
	protected double wait;
	
	/** Creates a new wait element with the specified wait time */
	protected WaitElement(double wait) {
		this.wait = wait;
	}
	
	/**
	 * Returns whether or not this element has an idle requirement during this
	 * iteration of the route.
	 */
	public abstract boolean hasWait();
	
	/**
	 * Returns whether or not this wait element will affect the bus traveling
	 * through. This method returns true if the wait element is imposing a
	 * velocity requirement.
	 */
	public abstract boolean hasDeltaV();
	
	/**
	 * Returns the velocity requirement this wait element is imposing. Should
	 * only be called if hasDeltaV() returned true.
	 */
	public abstract double getVelocity();
	
	/** Has the bus wait on this element for the required time */
	public void waitOn(Bus bus) {
		bus.idle(wait);
	}
}
