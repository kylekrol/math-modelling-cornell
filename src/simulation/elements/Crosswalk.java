package simulation.elements;

/**
 * Represents a crosswalk element in a bus route. A crosswalk element will,
 * with a specified stopping probability, cause the bus to come to a complete
 * stop and idle for a specified amount of time.
 */
public class Crosswalk extends WaitElement {

	/** Chance of stopping at the given crosswalk */
	private double stopChance;

	/** Creates a new crosswalk with the specified wait time and stop chance */
	public Crosswalk(double wait, double stopChance) {
		super(wait);
		this.stopChance = stopChance;
	}
	
	@Override
	public boolean hasWait() {
		return (WaitElement.rand.nextDouble() < stopChance);
	}

	@Override
	public boolean hasDeltaV() {
		return (WaitElement.rand.nextDouble() < stopChance);
	}

	@Override
	public double getVelocity() {
		return 0.0d;
	}
}
