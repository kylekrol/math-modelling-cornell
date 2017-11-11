package simulation;

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
	
	/** Returns the bus's current speed in meters per second */
	public double speed() {
		return v;
	}
	
	/**
	 * Calculates the fuel consumed for the given energy consumed.
	 * 
	 * @param e
	 * 		the energy consumed by the engine
	 */
	private void combustionEnergy(double e) {
		// TODO
	}
	
	public void accelerate(double vf, double grade) {
		// TODO
	}
	
	public void travel(double length, double grade) {
		// TODO
	}
	
	public void brake(double vf) {
		// TODO
	}
	
	public void idle(double time) {
		// TODO
	}
}
