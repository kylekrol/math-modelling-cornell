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
	
	/** Returns the bus's current speed in meters per second */
	public double speed() {
		return v;
	}

	/** Updates gas usage given an amount of energy e consumed in joules */
	private void runEngine(double e) {
		
		System.out.println(Constants.GAS_PER_JOULE * e / Constants.BUS_EFF);
		
		if(e >= 0)
			gasUsage += (Constants.GAS_PER_JOULE * e / Constants.BUS_EFF > 1.0d ? 0.0d : Constants.GAS_PER_JOULE * e / Constants.BUS_EFF);
		else
			System.err.println("Negative energy request revieved");
	}
	
	public void travel(double vf, double vlim, double dx, double dy) {
		double dk, du, dw;
		double sin = dy / dx;
		double ap = Constants.BUS_ACCEL - 9.81d * sin;
		double am = - Constants.BUS_DECCEL - 9.81d * sin;
		
		// Check if vf is attainable
		double a = (v > vf ? am : ap);
		double dxmin = (vf*vf - v*v) / 2*a1;
		if(dxmin >= dx) {
			// vf is not attainable on this leg
			double vend = Math.sqrt(v*v + 2*a*dx);
			dk = 0.5d * Constants.BUS_MASS * (vend*vend - v*v);
			du = Constants.BUS_MASS * 9.81d * dy;
			dw = workRes(a, v, dx);
			v = vend;
		} else {
			// vf is not attainable
			double a1 = (v > vlim ? am : ap);
			double a2 = (vf < vlim ? am : ap);
			double x1 = (vlim*vlim - v*v) / 2*a1;
			double x2 = (vf*vf - vlim*vlim) / 2*a2;
			if(x1 + x2 <= dx) {
				// Speed limit can be attained
				
			} else {
				
			}
		}
		runEngine(dk + du + dw);
		
		double dxp = (vlim*vlim - v*v) / 2*ap;
		double dxm = (vf*vf - vlim*vlim) / 2*am;
		
		if(dxp > dx) {
			System.out.println("Assertion");
		}
		
		double dxmin = dxp + dxm;
		
		if(dxmin <= dx) {
			// Desired vf is possible while attaining vlim
			double dk = 0.5d * Constants.BUS_MASS * (vlim*vlim - v*v);
			double du = Constants.BUS_MASS * 9.81d * dy;
			double dw = workRes(ap, v, dxp) + 
					    workRes(0.0d, vlim, dx - dxp - dxm) + 
					    workRes(am, vlim, dxm);
			
			//System.out.println("k from 1st: " + dk);
			
			runEngine(dk + du + dw);
		} else {
			// Desired vf cannot be reached
			double vmax = Math.sqrt( (2*am*ap*dx + am*v*v - ap*vf*vf) / (am - ap) );
			dxp = (vmax*vmax - v*v) / 2*ap;
			dxm = (vf*vf - vmax*vmax) / 2*am;
			
			double dk = 0.5d * Constants.BUS_MASS * (vlim*vlim - v*v);
			double du = Constants.BUS_MASS * 9.81d * dy;
			double dw = workRes(ap, v, dxp) + 
					    workRes(0.0d, vmax, dx - dxp - dxm) + 
					    workRes(am, vmax, dxm);
			
			//System.out.println("k from 2nd: " + dk);
			
			runEngine(dk + du + dw);
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

	public void brake(double vf) {
		v = (v < vf ? v : vf);
	}

	public void idle(double dt) {
		gasUsage += Constants.IDLE_GAL_PER_S * dt;
	}
}
