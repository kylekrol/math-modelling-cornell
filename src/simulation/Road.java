package simulation;

/**
 * Represents a road element in a bus route. A road element is a
 * section of road that a bus drives along at constant speed with no chance of
 * stopping.
 */
public class Road implements Element {
	
	/** Length of the road in meters */
	private double length;
	/** Expected travel speed in meters per second */
	private double speed;
	/** Grade of the road */
	private double grade;
	
	/** Set the parameters in a new Road object */
	Road(double speed, double length, double grade) {
		this.length = length;
		this.speed = speed;
		this.grade = grade;
	}
	
	/** Returns the length of the road in meters */
	public double getLength() {
		return length;
	}

	/** Returns the speed of the bus in meters per second */
	public double getSpeed() {
		return speed;
	}
	
	/** Returns the grade of the road */
	public double getGrade() {
		return grade;
	}

	@Override
	public void drive(Bus bus) {
		bus.accelerate(speed, grade);
		bus.travel(length, grade);
	}
}
