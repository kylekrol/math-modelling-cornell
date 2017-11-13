package console;

import java.io.BufferedOutputStream;
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