package console;

import java.io.FileNotFoundException;
import java.io.IOException;

import simulation.Route;
import simulation.buses.Bus;

public class Console {

	public static void main(String[] args) {
		try {
			
			long start = System.currentTimeMillis();
			
			Route route = RouteFactory.loadRoute("routes/rt11.txt");
			Bus bus = new Bus();
			
			System.out.println(System.lineSeparator() + "Rt 11");
			for(int i = 0; i < 10; i++) {
				route.drive(bus);
				System.out.println(bus.gasUsage());
			}
			
			route = RouteFactory.loadRoute("routes/rt10.txt");
			bus = new Bus();
			
			System.out.println(System.lineSeparator() + "Rt 10");
			for(int i = 0; i < 10; i++) {
				route.drive(bus);
				System.out.println(bus.gasUsage());
			}
			
			route = RouteFactory.loadRoute("routes/rt81.txt");
			bus = new Bus();
			
			System.out.println(System.lineSeparator() + "Rt 81");
			for(int i = 0; i < 10; i++) {
				route.drive(bus);
				System.out.println(bus.gasUsage());
			}
			
			System.out.println(System.lineSeparator() + "Time: " + (System.currentTimeMillis() - start));
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}