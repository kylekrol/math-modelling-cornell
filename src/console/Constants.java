package console;

/** Constants used in the simulation */
public class Constants {
	
	/** Convert mph to m/s */
	public static final double MPH_TO_METERS_PER_S = 0.44704d;
	/** Convert gallons of gas to joules */
	public static final double GAS_PER_JOULE = 1.0d / 135800000.0d;
	
	/** Bus acceleration in m/s^2 */
	public static final double BUS_ACCEL = 0.7d;
	/** Bus deceleration in m/s^2 */
	public static final double BUS_DECCEL = 1.1d;
	/** Turn speed in m/s^2 */
	public static final double TURN_SPEED = 5.0d * MPH_TO_METERS_PER_S;
	
	/** Bus engine efficiency */
	public static final double BUS_EFF = 0.4d;
	/** Bus mass */
	public static final double BUS_MASS = 13607.771d;
	/** Bus rolling friction factor for VSP */
	public static final double ROLL_FRIC = 0.092d;
	/** Bus drag constant for VSP */
	public static final double DRAG_CONST = 0.00021d;
	/** Gallons of gas used per second of idle time */
	public static final double IDLE_GAL_PER_S = 0.0002694444444d;

	/** Max usable energy stored in battery in watts */
	public static final double MAX_CHARGE = 166000d * 3600d;
	/** Brake energy regain efficiency */
	public static final double BRAKE_EFF = .15;//0.23d;
	/** Engine battery charging proportion */
	public static final double ENGINE_RECHARGE_PROP = 0.025d;
	/** Bus electric engine efficiency */
	public static final double BUS_ELECTRIC_EFF = .8;//0.95d;
	
	/** Chance of passing through a stop light */
	public static final double LIGHT_THROUGH_CHANCE = 0.5d;

}
