package simulation;

import simulation.buses.Bus;

/**
 * Represents a general bus route. A bus route is made of of a series of route
 * elements.
 */
public class Route {
	
	/** Start of the route element linked list */
	private Node start;
	/** Tail of the route element linked list */
	private Node tail;
	
	/** Creates a new route out of the given elements */
	public Route() {
		
	}
	
	/** Adds the passed route element to this route */
	public void add(Element element) {
		if(start == null) {
			start = new Node(element);
			tail = start;
		}
		tail.next = new Node(element);
		tail = tail.next;
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
	
	/**
	 * Drives the bus through a route a single time.
	 * 
	 * @param bus
	 * 		the bus driving through the route
	 */
	public void drive(Bus bus) {
		Node node = start;
		while(node != null) {
			node.element.drive(bus);
			node = node.next;
		}
	}
	
	/** Represents a route element in the route's linked list */
	private class Node {
		
		/** Route element at this node */
		private Element element;
		/** Pointer to the next element's node */
		private Node next;
		
		/** Constructs a new route element node */
		private Node(Element element) {
			this.element = element;
		}
	}
}
