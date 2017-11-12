package console;

import java.io.FileNotFoundException;
import java.io.IOException;

import simulation.Route;
import simulation.buses.Bus;
import simulation.buses.HybridBus;

public class Console {

	public static void main(String[] args) {
		
		getAllMPG();
		
		System.out.println();
		System.out.println("----------");
		System.out.println();
		
		

	}
	
	private static void getAllMPG() {
		
		final int ITERATIONS = 10000;
		
		try {
			Route route = RouteFactory.loadRoute("routes/rt11.txt");
			HybridBus hybrid = new HybridBus();
			Bus bus = new Bus();
			for(int i = 0; i < ITERATIONS; i++) {
				route.drive(bus);
				route.drive(hybrid);
			}
			System.out.println("Rt11");
			System.out.println("Bus: " + (bus.totalTravelled()*0.000621371d)/bus.gasUsage());
			System.out.println("Hybrid: " + (hybrid.totalTravelled()*0.000621371d)/hybrid.gasUsage());
			System.out.println();
			
			
			route = RouteFactory.loadRoute("routes/rt10.txt");
			hybrid = new HybridBus();
			bus = new Bus();
			for(int i = 0; i < ITERATIONS; i++) {
				route.drive(bus);
				route.drive(hybrid);
			}
			System.out.println("Rt10");
			System.out.println("Bus: " + (bus.totalTravelled()*0.000621371d)/bus.gasUsage());
			System.out.println("Hybrid: " + (hybrid.totalTravelled()*0.000621371d)/hybrid.gasUsage());
			System.out.println();
			
			
			route = RouteFactory.loadRoute("routes/rt15.txt");
			hybrid = new HybridBus();
			bus = new Bus();
			for(int i = 0; i < ITERATIONS; i++) {
				route.drive(bus);
				route.drive(hybrid);
			}
			System.out.println("Rt15");
			System.out.println("Bus: " + (bus.totalTravelled()*0.000621371d)/bus.gasUsage());
			System.out.println("Hybrid: " + (hybrid.totalTravelled()*0.000621371d)/hybrid.gasUsage());
			System.out.println();
			
			
			route = RouteFactory.loadRoute("routes/rt81.txt");
			hybrid = new HybridBus();
			bus = new Bus();
			for(int i = 0; i < ITERATIONS; i++) {
				route.drive(bus);
				route.drive(hybrid);
			}
			System.out.println("Rt81");
			System.out.println("Bus: " + (bus.totalTravelled()*0.000621371d)/bus.gasUsage());
			System.out.println("Hybrid: " + (hybrid.totalTravelled()*0.000621371d)/hybrid.gasUsage());
			System.out.println();
			
			
			route = RouteFactory.loadRoute("routes/rt82.txt");
			hybrid = new HybridBus();
			bus = new Bus();
			for(int i = 0; i < ITERATIONS; i++) {
				route.drive(bus);
				route.drive(hybrid);
			}
			System.out.println("Rt82");
			System.out.println("Bus: " + (bus.totalTravelled()*0.000621371d)/bus.gasUsage());
			System.out.println("Hybrid: " + (hybrid.totalTravelled()*0.000621371d)/hybrid.gasUsage());
			System.out.println();
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}