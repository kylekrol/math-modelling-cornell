package simulation;

import simulation.buses.Bus;
import simulation.elements.Road;
import simulation.elements.WaitElement;

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
		this.start = null;
		this.tail = null;
	}
	
	/** Adds the passed route element to this route */
	public void add(Object element) {
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
		Node current = processOnStop(bus, start);
		while(current != null) {
			if(current.isWaitElement())
				
			
			current = current.next;
		}
	}
	
	private Node processOnStop(Bus bus, Node next) {
		WaitElement element = null;
		while(next != null && next.isWaitElement()) {
			element = (WaitElement) next.element;
			if(element.hasWait())
				element.waitOn(bus);
			next = next.next;
		}
		return next;
	}
	
	/**
	 * Processes a bus behavior as it is passing through a soft turn (green
	 * light while the bus is turning). This method will return the next node
	 * of interest or null if the end of the route has been reached.
	 * 
	 * @param bus
	 * 		the bus being operated on
	 * @param next
	 * 		the next node in the route element list
	 * @return
	 * 		the next node of interest or null if the route is over
	 */
	private Node processOnRoad(Bus bus, Node current) {
		double v = 0.0d;
		Node node = current.next;
		while(node != null) {
			if(node.isWaitElement()) {
				if(((WaitElement) node.element).hasDeltaV()) {
					v = ((WaitElement) node.element).getVelocity();
					break;
				}
			} else {
				v = ((Road) node.element).speed();
				break;
			}
			node = node.next;
		}
		((Road) current.element).drive(bus, v);
		return node;
	}
	
	/**
	 * Processes a bus behavior as it is passing through a soft turn (green
	 * light while the bus is turning). This method will return the next node
	 * of interest or null if the end of the route has been reached.
	 * 
	 * @param bus
	 * 		the bus being operated on
	 * @param next
	 * 		the next node in the route element list
	 * @return
	 * 		the next node of interest or null if the route is over
	 */
	private Node processOnSoftTurn(Bus bus, Node next) {
		while(next != null) {
			if(next.isWaitElement()) {
				if(((WaitElement) next.element).hasDeltaV()) {
					double dv = ((WaitElement) next.element).getVelocity();
					if(dv == 0.0d) {
						// Stopped required so performed instantaneously
						bus.brake(0.0f);
						((WaitElement) next.element).waitOn(bus);
						break;
					} else {
						// Another soft turn so element ignored
					}
				} else {
					// No deltaV so element essentially ignored
				}
			} else {
				// Road element so return to main loop
				break;
			}
			next = next.next;
		}
		return next;
	}
	
	/** Represents a route element in the route's linked list */
	private class Node {
		
		/** Route element at this node */
		private Object element;
		/** Pointer to the next element's node */
		private Node next;
		
		/** Constructs a new route element node */
		private Node(Object element) {
			this.element = element;
		}
		
		/** Returns whether the element in this node is a wait element */
		private boolean isWaitElement() {
			return element instanceof WaitElement;
		}
	}
}
