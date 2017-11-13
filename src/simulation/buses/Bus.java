package simulation.buses;

import console.Constants;

/**
 * Represents a bus that can be run through a route's elements. Performs all
 * necessary kinematics and energy to fuel calculations here. 
 */
public class Bus {

	/** Gas used in gallons */
	private double gasUsage;
	/** Total distance traveled in meters */
	private double totalTraval;
	/** Current bus speed in meters per second */
	private double v;

	/** Creates a new bus object with zero gas usage and speed */
	public Bus() {
		this.totalTraval = 0.0d;
		this.gasUsage = 0;
		this.v = 0;
	}
	
	/** Resets the fuel and distance counters to zero */
	public void reset() {
		this.gasUsage = 0.0d;
		this.totalTraval = 0.0d;
	}
	
	/** Returns the gas mileage of the bus */
	public double gasMileage() {
		return totalTraval / gasUsage;
	}

	/** Returns the bus's current gas usage in gallons */
	public double gasUsage() {
		return gasUsage;
	}
	
	/** Returns the bus's current total travel distance in meters */
	public double totalTravelled() {
		return this.totalTraval;
	}
	
	/** Returns the bus's current speed in meters per second */
	public double speed() {
		return v;
	}

	/** 
	 * Updates gas usage given an amount of energy e consumed in joules. The
	 * sin argument is representative of the grade of the road and is important
	 * in the hybrid bus run function;
	 */
	protected void run(double e, double sin) {
		double gallons = Constants.GAS_PER_JOULE * e / Constants.BUS_EFF;
		if(gallons == Double.NaN || gallons > 0.1d)
			System.err.println("Energy request of " + gallons + " gal");
		if(e >= 0)
			gasUsage += gallons;
	}
	
	public void travel(double vf, double vlim, double dx, double dy) {
		this.totalTraval += dx;
		double sin = dy / dx;
		// Check if vf is attainable
		double a = (v > vf ? -Constants.BUS_DECCEL : Constants.BUS_ACCEL);
		double dxmin = (vf*vf - v*v) / (2*a);
		if(dxmin > dx) {
			// vf is not attainable on this leg
			double vend = Math.sqrt(v*v + 2*a*dx);
			double dk = 0.5d * Constants.BUS_MASS * (vend*vend - v*v);
			double du = Constants.BUS_MASS * 9.81d * dy;
			double dw = workRes(a, v, dx);
			run(dk + du + dw, sin);
			v = vend;
		} else {
			// vf is attainable
			double a1 = (v > vlim ? -Constants.BUS_DECCEL : Constants.BUS_ACCEL);
			double a2 = (vf < vlim ? -Constants.BUS_DECCEL : Constants.BUS_ACCEL);
			double dx1 = (vlim*vlim - v*v) / (2*a1);
			double dx2 = (vf*vf - vlim*vlim) / (2*a2);
			if(dx1 + dx2 <= dx) {
				// Speed limit can be attained
				double dk, du, dw;
				// Acceleration segment one
				dk = 0.5d * Constants.BUS_MASS * (vlim*vlim - v*v);
				du = Constants.BUS_MASS * 9.81d * dx1 * sin;
				dw = workRes(a1, v, dx1);
				run(dk + du + dw, sin);
				// Zero acceleration segment
				du = Constants.BUS_MASS * 9.81d * (dx - dx1 - dx2) * sin;
				dw = workRes(0.0d, vlim, dx - dx1 - dx2);
				run(du + dw, sin);
				// Acceleration segment two
				dk = 0.5d * Constants.BUS_MASS * (vf*vf - vlim*vlim);
				du = Constants.BUS_MASS * 9.81d * dx2 * sin;
				dw =  workRes(a2, vlim, dx2);
				run(dk + du + dw, sin);
				v = vf;
			} else {
				// Speed limit cannot be attained
				double dk, du, dw;
				double vmax = Math.sqrt( (2*a1*a2*dx + a2*v*v - a1*vf*vf) / (a2 - a1) );
				dx1 = (vmax*vmax - v*v) / (2*a1);
				dx2 = (vf*vf - vmax*vmax) / (2*a2);
				// Acceleration segment one
				dk = 0.5d * Constants.BUS_MASS * (vmax*vmax - v*v);
				du = Constants.BUS_MASS * 9.81d * dx1 * sin;
				dw = workRes(a1, v, dx1);
				run(dk + du + dw, sin);
				// Acceleration segment two
				dk = 0.5d * Constants.BUS_MASS * (vf*vf - vmax*vmax);
				du = Constants.BUS_MASS * 9.81d * dx2 * sin;
				dw = workRes(a2, vmax, dx2);
				run(dk + du + dw, sin);
				v = vf;
			}
		}
	}
	
	/**
	 * Calculate work done by friction and air resistance over an interval of
	 * constant acceleration
	 */
	private double workRes(double a, double vi, double dx) {
		return (Constants.BUS_MASS * Constants.DRAG_CONST * vi*vi)*dx +
				Constants.BUS_MASS * Constants.DRAG_CONST * a * dx*dx;
	}

	/**
	 * Decreases the speed of the bus to vf if necessary. If v is already below
	 * vf no action is taken
	 */
	public void brake(double vf) {
		v = (v < vf ? v : vf);
	}

	/** Has the bus idle for the specified time interval */
	public void idle(double dt) {
		gasUsage += Constants.IDLE_GAL_PER_S * dt;
	}
}
