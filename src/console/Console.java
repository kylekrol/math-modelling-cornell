package console;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import simulation.Route;
import simulation.Route.DataPrinter;
import simulation.buses.Bus;
import simulation.buses.HybridBus;

public class Console {

	public static void main(String[] args) {
		
		getAllMPG();
		
		System.out.println();
		System.out.println("----------");
		System.out.println();
		
		getAllMPGData();
		
		System.out.println("Complete");

	}
	
	
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
				if(Double.isNaN(gm)) System.err.println("NaN gas");
				hybrid.reset();
				return gm + ",";
			}
		});
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
			System.out.println("Saved:  " + (bus.gasUsage() - hybrid.gasUsage()) / ITERATIONS);
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
			System.out.println("Saved:  " + (bus.gasUsage() - hybrid.gasUsage()) / ITERATIONS);
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
			System.out.println("Saved:  " + (bus.gasUsage() - hybrid.gasUsage()) / ITERATIONS);
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
			System.out.println("Saved:  " + (bus.gasUsage() - hybrid.gasUsage()) / ITERATIONS);
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
			System.out.println("Saved:  " + (bus.gasUsage() - hybrid.gasUsage()) / ITERATIONS);
			System.out.println();
			
			
			route = RouteFactory.loadRoute("routes/rt17.txt");
			hybrid = new HybridBus();
			bus = new Bus();
			for(int i = 0; i < ITERATIONS; i++) {
				route.drive(bus);
				route.drive(hybrid);
			}
			System.out.println("Rt17");
			System.out.println("Bus: " + (bus.totalTravelled()*0.000621371d)/bus.gasUsage());
			System.out.println("Hybrid: " + (hybrid.totalTravelled()*0.000621371d)/hybrid.gasUsage());
			System.out.println("Saved:  " + (bus.gasUsage() - hybrid.gasUsage()) / ITERATIONS);
			System.out.println();
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*
Rt11
Bus: 6.798771825662416
Hybrid: 10.643130866792973
Saved:  0.40083467339221224

Rt10
Bus: 5.403425578888811
Hybrid: 8.980662712184332
Saved:  0.22573133481224728

Rt15
Bus: 5.665604154253449
Hybrid: 10.533093675730612
Saved:  0.3687116236003167

Rt81
Bus: 7.240899587478843
Hybrid: 13.59985246758139
Saved:  0.25796054805579754

Rt82
Bus: 7.468545561172195
Hybrid: 12.834424425014292
Saved:  0.5332038015105971

Rt17
Bus: 7.984665352908379
Hybrid: 12.751478148924123
Saved:  0.14554333753588175


----------

Complete

	 */
}