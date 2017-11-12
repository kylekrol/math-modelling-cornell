package simulation.buses;

import console.Constants;

/**
 * Represents a hybrid bus. Overrides functions from Bus to change how the
 * energy is handled
 */
public class HybridBus extends Bus {

	/** Usable energy store din the battery in kWhrs */
	private double batCharge;
	
	/** Returns the battery's current charge */
	public double batCharge() {
		return batCharge;
	}
	
	public HybridBus() {
		super();
		batCharge = 0.0d;
	}
	
	@Override
	protected void run(double e, double sin) {
		if(e <= 0.0d) {
			// Energy put into battery
			batCharge -= Constants.BRAKE_EFF * e;
			batCharge = (batCharge > Constants.MAX_CHARGE ? Constants.MAX_CHARGE : batCharge);
		} else {
			// Energy required from engine/battery
			if(sin > 0.0d) {
				// Steep grade so use engine
				super.run(e, sin);
				batCharge += e*Constants.ENGINE_RECHARGE_PROP;
			} else {
				// Flat ground
				double batmax = batCharge * Constants.BUS_ELECTRIC_EFF;
				batmax -= e;
				if(batmax >= 0) {
					// Battery took full charge
					batCharge = batmax / Constants.BUS_ELECTRIC_EFF;
				} else {
					// Battery depleted and engine provides rest of power
					super.run(-batmax, sin);
					batCharge = -batmax*Constants.ENGINE_RECHARGE_PROP; // Battery recharge begins
				}
			}
		}
	}
	
	@Override
	public void brake(double vf) {
		if(vf < super.speed()) {
			double dk = 0.5d * Constants.BUS_MASS * (super.speed()*super.speed() - vf*vf);
			batCharge += dk*Constants.BRAKE_EFF;
		}
		super.brake(vf);
	}
	
	@Override
	public void idle(double dt) {
		// Hybrid never idles
	}
}
