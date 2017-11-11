package simulation;

public interface Element {

	/**
	 * Calculates this route elements action on the specified bus and it's
	 * data.
	 * 
	 * @param bus
	 * 		the bus acted on by this element
	 */
	public void drive(Bus bus);
	
}
