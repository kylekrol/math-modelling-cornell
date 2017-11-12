package simulation.buses;

import console.Constants;

public class HybridBus extends Bus {

	/** Usable energy store din the battery in kWhrs */
	private double batCharge;
	
	@Override
	protected void run(double e, double sin) {
		if(e < 0.0d) {
			// Energy put into battery
			batCharge -= Constants.BRAKE_EFF * e;
			batCharge = (batCharge > Constants.MAX_CHARGE ? Constants.MAX_CHARGE : batCharge);
		} else {
			// Energy required from engine/battery
			if(sin > 0.0d) {
				// Steep grade so use engine
				super.run(e, sin);
			} else {
				// Flat ground
				
			}
		}
	}
	
	@Override
	public void brake(double v) {
		
	}
	
	@Override
	public void idle(double dt) {
		// Hybrid never idles
	}
}
