package simulation;

public class Road implements Element {
	
	/** Length of the road in feet */
	private double length;
	/** Expected travel speed in feet per second */
	private double speed;
	/** Pitch of the road in grade */
	private double pitch;
	
	/** Set the parameters in a new Road object */
	Road(double speed, double length, double pitch) {
		this.length = length;
		this.speed = speed;
		this.pitch = pitch;
	}

	@Override
	public void drive(Bus bus) {
		bus.accelerate(speed);
		
	}
}
