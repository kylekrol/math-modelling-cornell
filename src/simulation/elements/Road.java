package simulation.elements;

import simulation.Element;
import simulation.buses.Bus;

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
	/** Elevation change in the road */
	private double elevation;
	
	/** Set the parameters in a new Road object */
	public Road(double speed, double length, double elevation) {
		this.length = length;
		this.speed = speed;
		this.elevation = elevation;
	}

	@Override
	public void drive(Bus bus) {
		bus.travel(speed, length, elevation);
	}
}
