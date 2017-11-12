package console;

import java.io.FileNotFoundException;
import java.io.IOException;

import simulation.Route;
import simulation.buses.Bus;

public class Console {

	public static void main(String[] args) {
		try {
			
			Route route = RouteFactory.loadRoute("routes/rt11.txt");
			Bus bus = new Bus();
			
			for(int i = 0; i < 10; i++) {
				route.drive(bus);
				System.out.println((bus.totalTravelled()*0.000621371d)/bus.gasUsage());
			}
			
			route = RouteFactory.loadRoute("routes/rt10.txt");
			bus = new Bus();
			
			route = RouteFactory.loadRoute("routes/rt81.txt");
			bus = new Bus();
			
			route = RouteFactory.loadRoute("routes/rt15.txt");
			bus = new Bus();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}