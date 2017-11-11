package simulation;

public class Bus {

	/** Gas used in gallons */
	private double gasUsage;
	/** Current bus speed in feet per second */
	private double speed;
	
	/** Creates a new bus object with zero gas usage and speed */
	public Bus() {
		this.gasUsage = 0;
		this.speed = 0;
	}
	
	/** Returns the bus's current speed in feet per second */
	double speed() {
		return speed;
	}
	
	// Friction
	void traverse(double length) {
		
	}
	
	// Potential energy
	void climb(double height) {
		
	}
	
	// Speed up
	void accelerate(double vf) {
		
	}
	
	// Slow down
	void brake(double vf) {
		
	}
}
