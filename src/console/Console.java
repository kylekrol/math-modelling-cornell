package console;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import simulation.Route;
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
		
		getAllGasTotals();
		
		getRouteGasIncrements();
		
		System.out.println("Complete");

	}
	
	private static void getRouteGasIncrements() {
		BufferedOutputStream out = null;
		try {
			
			Bus bus = new Bus();
			out = new BufferedOutputStream(new FileOutputStream("data/stp/rt10.txt"));
			Route route = RouteFactory.loadRoute("routes/rt10.txt");
			route.driveGasUsage(bus, out);
			out.close();
			
			bus = new Bus();
			out = new BufferedOutputStream(new FileOutputStream("data/stp/rt11.txt"));
			route = RouteFactory.loadRoute("routes/rt11.txt");
			route.driveGasUsage(bus, out);
			out.close();
			
			bus = new Bus();
			out = new BufferedOutputStream(new FileOutputStream("data/stp/rt15.txt"));
			route = RouteFactory.loadRoute("routes/rt15.txt");
			route.driveGasUsage(bus, out);
			out.close();
			
			bus = new Bus();
			out = new BufferedOutputStream(new FileOutputStream("data/stp/rt17.txt"));
			route = RouteFactory.loadRoute("routes/rt17.txt");
			route.driveGasUsage(bus, out);
			out.close();
			
			bus = new Bus();
			out = new BufferedOutputStream(new FileOutputStream("data/stp/rt81.txt"));
			route = RouteFactory.loadRoute("routes/rt81.txt");
			route.driveGasUsage(bus, out);
			out.close();
			
			bus = new Bus();
			out = new BufferedOutputStream(new FileOutputStream("data/stp/rt82.txt"));
			route = RouteFactory.loadRoute("routes/rt82.txt");
			route.driveGasUsage(bus, out);
			out.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Writes gas usage data over a root to a text file. 1000 trials are
	 * recorded.
	 */
	private static void getAllGasTotals() {
		try {
			
			getGasData("data/gas/rt10gas.txt","routes/rt10.txt");
			getGasData("data/gas/rt11gas.txt","routes/rt11.txt");
			getGasData("data/gas/rt15gas.txt","routes/rt15.txt");
			getGasData("data/gas/rt17gas.txt","routes/rt17.txt");
			getGasData("data/gas/rt81gas.txt","routes/rt81.txt");
			getGasData("data/gas/rt82gas.txt","routes/rt82.txt");
			
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	/** Writes gas usage data for a single route to the specified data file */
	private static void getGasData(String data, String source) throws IOException {
		BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(data));
		try {
			Route route = RouteFactory.loadRoute(source);
			HybridBus hybrid = new HybridBus();
			Bus bus = new Bus();
			for(int i = 0; i < 999; i++) {
				route.drive(bus);
				route.drive(hybrid);
				out.write((bus.gasUsage() + "," + hybrid.gasUsage() + System.lineSeparator()).getBytes());
				bus.reset();
				hybrid.reset();
			}
			route.drive(bus);
			route.drive(hybrid);
			out.write((bus.gasMileage() + "," + hybrid.gasMileage()).getBytes());
		} finally {
			out.close();
		}
	}
	
	/**
	 * Writes gas mileage data over a root to a text file. 1000 trials are
	 * recorded.
	 */
	private static void getAllMPGData() {
		try {
			
			getMPGData("data/mpg/rt10mpg.txt","routes/rt10.txt");
			getMPGData("data/mpg/rt11mpg.txt","routes/rt11.txt");
			getMPGData("data/mpg/rt15mpg.txt","routes/rt15.txt");
			getMPGData("data/mpg/rt17mpg.txt","routes/rt17.txt");
			getMPGData("data/mpg/rt81mpg.txt","routes/rt81.txt");
			getMPGData("data/mpg/rt82mpg.txt","routes/rt82.txt");
			
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	/** Writes gas mileage data for a single route to the specified data file */
	private static void getMPGData(String data, String source) throws IOException {
		BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(data));
		try {
			Route route = RouteFactory.loadRoute(source);
			HybridBus hybrid = new HybridBus();
			Bus bus = new Bus();
			for(int i = 0; i < 999; i++) {
				route.drive(bus);
				route.drive(hybrid);
				out.write((bus.gasMileage() + "," + hybrid.gasMileage() + System.lineSeparator()).getBytes());
				bus.reset();
				hybrid.reset();
			}
			route.drive(bus);
			route.drive(hybrid);
			out.write((bus.gasMileage() + "," + hybrid.gasMileage()).getBytes());
		} finally {
			out.close();
		}
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