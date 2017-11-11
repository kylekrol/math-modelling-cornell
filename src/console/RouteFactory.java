package console;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import simulation.Element;
import simulation.Route;
import simulation.elements.BusStop;
import simulation.elements.Crosswalk;
import simulation.elements.Road;
import simulation.elements.StopLight;
import simulation.elements.StopSign;

public class RouteFactory {

	public static Route loadRoute(String file) throws FileNotFoundException, IOException {
		BufferedReader reader = null;
		List<Element> elements = new LinkedList<Element>();
		try {
			int i = 0;
			String line;
			reader = new BufferedReader(new FileReader(file));
			while((line = reader.readLine().toLowerCase()) != null) {
				i++;
				if(line.startsWith("rd"))
					elements.add(loadRoad(line.split(" "), i));
				else if(line.startsWith("bs"))
					elements.add(loadBusStop(line.split(" "), i));
				else if(line.startsWith("ss"))
					elements.add(loadStopSign(line.split(" "), i));
				else if(line.startsWith("sl"))
					elements.add(loadStopLight(line.split(" "), i));
				else if(line.startsWith("cs"))
					elements.add(loadCrosswalk(line.split(" "), i));
				else if(!line.startsWith("//"))
					System.err.println("Unexpected route element type on line " + i);
			}
		} finally {
			if(reader != null) reader.close();
		}
		return new Route(elements);
	}
	
	private static Road loadRoad(String[] line, int i) {
		double speed = 0.0d, length = 0.0d, elevation = 0.0d;
		try {
			if(line.length < 4) throw new NumberFormatException();
			speed = Double.parseDouble(line[1]) * 0.44704d;
			length = Double.parseDouble(line[2]);
			elevation  = Double.parseDouble(line[3]);
		} catch(NumberFormatException e) {
			System.err.println("Imporperly formatted Road on line " + i);
			System.exit(-1);
		}
		return new Road(speed, length, elevation);
	}
	
	private static BusStop loadBusStop(String[] line, int i) {
		double congestion = 0.0d;
		try {
			if(line.length < 2) throw new NumberFormatException();
			congestion = Double.parseDouble(line[1]);
		} catch(NumberFormatException e) {
			System.err.println("Imporperly formatted BusStop on line " + i);
			System.exit(-1);
		}
		return new BusStop(congestion);
	}
	
	private static StopSign loadStopSign(String[] line, int i) {
		double congestion = 0.0d;
		try {
			if(line.length < 2) throw new NumberFormatException();
			congestion = Double.parseDouble(line[1]);
		} catch(NumberFormatException e) {
			System.err.println("Imporperly formatted BusStop on line " + i);
			System.exit(-1);
		}
		return new StopSign(congestion);
	}
	
	private static StopLight loadStopLight(String[] line, int i) {
		double congestion = 0.0d;
		try {
			if(line.length < 2) throw new NumberFormatException();
			congestion = Double.parseDouble(line[1]);
		} catch(NumberFormatException e) {
			System.err.println("Imporperly formatted StopLight on line " + i);
			System.exit(-1);
		}
		return new StopLight(congestion);
	}
	
	private static Crosswalk loadCrosswalk(String[] line, int i) {
		double congestion = 0.0d;
		try {
			if(line.length < 2) throw new NumberFormatException();
			congestion = Double.parseDouble(line[1]);
		} catch(NumberFormatException e) {
			System.err.println("Imporperly formatted Crosswalk on line " + i);
			System.exit(-1);
		}
		return new Crosswalk(congestion);
	}
}
