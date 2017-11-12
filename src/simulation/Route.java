package simulation;

import java.io.IOException;
import java.io.OutputStream;

import simulation.buses.Bus;
import simulation.elements.Road;
import simulation.elements.WaitElement;

/**
 * Represents a general bus route. A bus route is made of of a series of route
 * elements.
 */
public class Route {
	
	/** Interface allowing choice data printing from the Console class */
	public interface DataPrinter {
		public String line();
	}
	
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
	 * Drives a bus through the route a specified number of times and writes
	 * the data to the output stream via the provided data printer method
	 * @throws IOException 
	 */
	public void drive(Bus bus, int n, OutputStream print, DataPrinter data) throws IOException {
		for(int i = 0; i < n; i++) {
			drive(bus);
			print.write(data.line().getBytes());
			print.write(System.lineSeparator().getBytes());
		}
	}
	
	/**
	 * Drives the bus through a route a single time.
	 * 
	 * @param bus
	 * 		the bus driving through the route
	 */
	public void drive(Bus bus) {
		Node node = processOnStop(bus, start);
		while(node != null) {
			if(node.element instanceof Road) {
				node = processOnRoad(bus, node);
			} else if(bus.speed() == 0.0d) {
					node = processOnStop(bus, node); 
			} else {
				node = processOnSoftTurn(bus, node);
			}
		}
	}
	
	/**
	 * Traverse all subsequent wait elements and return once a road element
	 * or the end of the route has been discovered.
	 * 
	 * @param bus
	 * @param next
	 * @return
	 */
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
		boolean onWaitElement = false;
		Node node = current.next;
		while(node != null) {
			if(node.isWaitElement()) {
				if(((WaitElement) node.element).hasDeltaV()) {
					v = ((WaitElement) node.element).getVelocity();
					onWaitElement = true;
					break;
				}
			} else {
				v = ((Road) node.element).speed();
				onWaitElement = false;
				break;
			}
			node = node.next;
		}
		((Road) current.element).drive(bus, v);
		if(onWaitElement) bus.brake(v);
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
