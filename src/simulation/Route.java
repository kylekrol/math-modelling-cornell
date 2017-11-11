package simulation;

import java.util.List;

/**
 * Represents a general bus route. A bus route is made of of a series of route
 * elements.
 */
public class Route {

	/** List of route elements */
	private List<Element> elements;
	
	/**  */
	Route(List<Element> elements) {
		this.elements = elements;
	}
	
	/**
	 * Drives the bus through a route a specified number of times.
	 * 
	 * @param bus
	 * 		the bus driving through the route
	 * @param n
	 * 		the number of times the bus will drive the route
	 */
	public void drive(Bus bus, int n) {
		for(int i = 0; i < n; i++)
			drive(bus);
	}
	
	public void drive(Bus bus) {
		for(Element element:elements)
			element.drive(bus);
	}
}
