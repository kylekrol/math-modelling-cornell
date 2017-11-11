package simulation;

import java.util.Random;

/**
 * Represents any element that has the potential to cause the bus to idle
 * during a route.
 */
public abstract class WaitElement implements Element {
	
	/** Random number generator object for wait elements */
	protected static final Random rand = new Random();

	/** Expected wait time for this element */
	protected double wait;
	
	/** Creates a new wait element with the specified wait time */
	protected WaitElement(double wait) {
		this.wait = wait;
	}
}
