package simulation;

public class Bus {

	/** Gas used in gallons */
	private double gasUsage;
	/** Current bus speed in meters per second */
	private double speed;
	
	/** Creates a new bus object with zero gas usage and speed */
	public Bus() {
		this.gasUsage = 0;
		this.speed = 0;
	}
	
	/** Returns the bus's current speed in meters per second */
	double speed() {
		return speed;
	}
	
	void drive(Road road) {
		// TODO
	}
}
