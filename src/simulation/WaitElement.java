package simulation;

import java.util.Random;

public abstract class WaitElement implements Element {

	private double congestion;
	
	protected WaitElement(double congestion) {
		this.congestion = congestion;
	}
}
