package console;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import simulation.Route;
import simulation.Route.DataPrinter;
import simulation.buses.Bus;
import simulation.buses.HybridBus;

/** Main class for the simulation */
public class Console {

	/** Program starts from here */
	public static void main(String[] args) {
		
		getAllMPG();
		
		System.out.println();
		System.out.println("----------");
		System.out.println();
		
		getAllMPGData();
		
		System.out.println("Complete");

	}
	
	/**
	 * Writes gas mileage data over a root to a text file. 1000 trials are
	 * recorded.
	 */
	private static void getAllMPGData() {
		try {
			
			getMPGData("data/rt10mpg.txt","routes/rt10.txt");
			getMPGData("data/rt11mpg.txt","routes/rt11.txt");
			getMPGData("data/rt15mpg.txt","routes/rt15.txt");
			getMPGData("data/rt17mpg.txt","routes/rt17.txt");
			getMPGData("data/rt81mpg.txt","routes/rt81.txt");
			getMPGData("data/rt82mpg.txt","routes/rt82.txt");
			
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	/** Writes gas mileage data for a single route to the specified data file */
	private static void getMPGData(String data, String source) throws IOException {
		BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(data));
		Route route = RouteFactory.loadRoute(source);
		HybridBus hybrid = new HybridBus();
		Bus bus = new Bus();
		route.drive(bus, 1000, out, new DataPrinter() {
			@Override public String line() {
				double gm = bus.gasMileage();
				bus.reset();
				return gm + ",";
			}
		});
		out.write(System.lineSeparator().getBytes());
		route.drive(hybrid, 1000, out, new DataPrinter() {
			@Override public String line() {
				double gm = hybrid.gasMileage();
				hybrid.reset();
				return gm + ",";
			}
		});
	}
	
	/**
	 * Print out average gas mileage over each route for both buses over 1000
	 * iterations. The gallons of gas saved per route by driving the hybrid is
	 * also displayed
	 */
	private static void getAllMPG() {
		try {
			getMPG("rt10");
			getMPG("rt11");
			getMPG("rt15");
			getMPG("rt17");
			getMPG("rt81");
			getMPG("rt82");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/** Prints out data for average MPG and saved gallons of gas per loop */
	private static void getMPG(String file) throws IOException {
		Route route = RouteFactory.loadRoute("routes/" + file + ".txt");
		HybridBus hybrid = new HybridBus();
		Bus bus = new Bus();
		for(int i = 0; i < 1000; i++) {
			route.drive(bus);
			route.drive(hybrid);
		}
		System.out.println(file);
		System.out.println("Bus:    " + (bus.totalTravelled()*0.000621371d)/bus.gasUsage());
		System.out.println("Hybrid: " + (hybrid.totalTravelled()*0.000621371d)/hybrid.gasUsage());
		System.out.println("Saved:  " + (bus.gasUsage() - hybrid.gasUsage()) / 1000.0d);
		System.out.println();
	}
}