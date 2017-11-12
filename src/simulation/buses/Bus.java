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
	private void runEngine(double e) {
		if(e >= 0)
			gasUsage += Constants.GAS_PER_JOULE * e / Constants.BUS_EFF;
		else
			System.err.println("Negative energy request revieved");
	}

	/**
	 * Simulates the bus traveling a segment of road with the provided speed,
	 * distance, and elevation change. From these values, the change in kinetic
	 * energy, change in potential, and energy work of frictions are
	 * calculated and applied to the engine.
	 * 
	 * @param vf
	 * 		the speed the bus will travel the road at
	 * @param x
	 * 		the distance being traveled
	 * @param dy
	 * 		the elevation change over the travel distance
	 */
	public void travel(double vf, double x, double dy) {
		double k = 0.5d * Constants.BUS_MASS * (vf*vf - v*v);
		// double u = Constants.BUS_MASS * 9.81d * dy;
		// double w = Constants.BUS_MASS * x * (Constants.ROLL_FRIC + Constants.DRAG_CONST * vf*vf);
		runEngine(k /*+ u + w*/);
	}

	public void brake(double vf) {
		v = (v < vf ? v : vf);
	}

	public void idle(double dt) {
		gasUsage += Constants.IDLE_GAL_PER_S * dt;
	}
}
