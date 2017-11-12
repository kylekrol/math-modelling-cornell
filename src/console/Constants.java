package console;

public class Constants {
	
	public static final double MPH_TO_METERS_PER_S = 0.44704d;
	public static final double GAS_PER_JOULE = 1.0d / 135800000.0d;
	
	public static final double BUS_MASS = 13607.771d;
	public static final double BUS_EFF = 0.4d;
	public static final double TURN_SPEED = 5.0d * MPH_TO_METERS_PER_S;
	public static final double ROLL_FRIC = 0.092d;
	public static final double DRAG_CONST = 0.00021d;
	public static final double IDLE_GAL_PER_S = 0.0002694444444d;

	public static final double MAX_CHARGE = 1.0d;
	public static final double MIN_CHARGE = 0.1d;
	public static final double MAX_RECHARGE = 0.1d;
	
	public static final double BUS_STOP_WAIT = 10.0d;
	public static final double STOP_SIGN_WAIT = 30.0d;
	public static final double STOP_LIGHT_WAIT = 120.0d;
	public static final double LIGHT_THROUGH_CHANCE = 0.5d;

}
