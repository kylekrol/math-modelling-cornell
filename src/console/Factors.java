package console;

public class Factors {

	private static double MAX_CHARGE = 1.0d;
	private static double MIN_CHARGE = 0.1d;
	private static double MAX_RECHARGE = 0.1d;
	
	public static double MAX_CHARGE() {
		return MAX_CHARGE;
	}
	
	public static double MIN_CHARGE() {
		return MIN_CHARGE;
	}
	
	public static double MAX_RECHARGE() {
		return MAX_RECHARGE;
	}
	
	private static double BUS_STOP_WAIT = 10.0d;
	private static double STOP_LIGHT_WAIT = 120.0d;
	private static double STOP_SIGN_WAIT = 30.0d;

	public static double BUS_STOP_WAIT() {
		return BUS_STOP_WAIT;
	}

	public static double STOP_LIGHT_WAIT() {
		return STOP_LIGHT_WAIT;
	}

	public static double STOP_SIGN_WAIT() {
		return STOP_SIGN_WAIT;
	}	
}
