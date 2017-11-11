package simulation.buses;

import console.Constants;

public class Bus {

	/** Gas used in gallons */
	private double gasUsage;
	/** Current bus speed in meters per second */
	private double v;

	/** Creates a new bus object with zero gas usage and speed */
	public Bus() {
		this.gasUsage = 0;
		this.v = 0;
	}

	/** Returns the bus's current gas usage in gallons */
	public double gasUsage() {
		return gasUsage;
	}

	/** Updates gas usage given an amount of energy e consumed in joules */
	private void combustionEnergy(double e) {
		if(e >= 0)
			gasUsage += Constants.GAS_PER_JOULE * e / Constants.BUSS_EFF;
		else
			System.err.println("Negative energy request revieved");
	}

	/**
	 * Applies an acceleration energy cost to the bus after it increases it's
	 * speed to the specified final velocity.
	 * 
	 * @param vf
	 * 		the final velocity of the bus in meters per second
	 * @param grade
	 * 		the current grade of the road
	 */
	public void accelerate(double vf, double grade) {
		assert vf > v;
		combustionEnergy(0.5d * Constants.BUSS_MASS * (vf*vf - v*v));
		v = vf;
	}

	public void travel(double length, double elevation) {
		double t = Constants.BUSS_MASS * length * (Constants.ROLL_FRIC + Constants.DRAG_CONST * v*v);
		double u = Constants.BUSS_MASS * 9.81d * elevation;
		combustionEnergy(t + u);
	}

	public void brake(double vf) {
		v = vf;
	}

	public void idle(double time) {
		// TODO
	}
}
