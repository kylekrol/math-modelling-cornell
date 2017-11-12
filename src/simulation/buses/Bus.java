package simulation.buses;

import console.Constants;

public class Bus {

	/** Gas used in gallons */
	private double gasUsage;
	
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

	/** Updates gas usage given an amount of energy e consumed in joules */
	private void run(double e) {
		double gallons = Constants.GAS_PER_JOULE * e / Constants.BUS_EFF;
		if(gallons == Double.NaN || gallons > 0.1d)
			System.err.println("Energy request of " + gallons + " gal");
		if(e >= 0)
			gasUsage += gallons;
	}
	
	public void travel(double vf, double vlim, double dx, double dy) {
		this.totalTraval += dx;
		
		double dk1, dk2, du, dw;
		double sin = dy / dx;
		double ap = Constants.BUS_ACCEL - 9.81d * sin;
		double am = -Constants.BUS_DECCEL - 9.81d * sin;
		
		// Check if vf is attainable
		double a = (v > vf ? am : ap);
		double dxmin = (vf*vf - v*v) / 2*a;
		if(dxmin >= dx) {
			// vf is not attainable on this leg
			double vend = Math.sqrt(v*v + 2*a*dx);
			dk1 = 0.5d * Constants.BUS_MASS * (vend*vend - v*v);
			du = Constants.BUS_MASS * 9.81d * dy;
			dw = workRes((a > 0 ? Constants.BUS_ACCEL : -Constants.BUS_DECCEL), v, dx);
			v = vend;
		} else {
			// vf is not attainable
			double a1 = (v > vlim ? am : ap);
			double a2 = (vf < vlim ? am : ap);
			double dx1 = (vlim*vlim - v*v) / 2*a1;
			double dx2 = (vf*vf - vlim*vlim) / 2*a2;
			if(dx1 + dx2 <= dx) {
				// Speed limit can be attained
				dk1 = 0.5d * Constants.BUS_MASS * (vlim*vlim - v*v);
				dk2 = 0.5d * Constants.BUS_MASS * (vf*vf - vlim*vlim);
				du = Constants.BUS_MASS * 9.81d * dy;
				dw = workRes((a1 > 0 ? Constants.BUS_ACCEL : -Constants.BUS_DECCEL), v, dx1) + 
					    workRes(0.0d, vlim, dx - dx1 - dx2) + 
					    workRes((a2 > 0 ? Constants.BUS_ACCEL : -Constants.BUS_DECCEL), vlim, dx2);
				v = vf;
			} else {
				// Speed limit cannot be attained
				double vmax = Math.sqrt( (2*a1*a2*dx + a2*v*v - a1*vf*vf) / (a2 - a1) );
				dx1 = (vmax*vmax - v*v) / 2*ap;
				dx2 = (vf*vf - vmax*vmax) / 2*am;
				dk1 = 0.5d * Constants.BUS_MASS * (vmax*vmax - v*v);
				dk2 = 0.5d * Constants.BUS_MASS * (vf*vf - vmax*vmax);
				du = Constants.BUS_MASS * 9.81d * dy;
				dw = workRes((a1 > 0 ? Constants.BUS_ACCEL : -Constants.BUS_DECCEL), v, dx1) + 
					    workRes(0.0d, vmax, dx - dx1 - dx2) + 
					    workRes((a2 > 0 ? Constants.BUS_ACCEL : -Constants.BUS_DECCEL), vmax, dx2);
				v = vf;
			}
		}
		run(dk1);
		run(dk1);
		run(du + dw);
	}
	
	/**
	 * Calculate work done by friction and air resistance over an interval of
	 * constant acceleration
	 */
	private double workRes(double a, double vi, double dx) {
		return (Constants.BUS_MASS * Constants.DRAG_CONST * vi*vi)*dx +
				Constants.BUS_MASS * Constants.DRAG_CONST * a * dx*dx;
	}

	public void brake(double vf) {
		v = (v < vf ? v : vf);
	}

	public void idle(double dt) {
		gasUsage += Constants.IDLE_GAL_PER_S * dt;
	}
}
