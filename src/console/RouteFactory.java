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
				if(line.startsWith("road"))
					elements.add(loadRoad(line, i));
				else if(line.startsWith("busstop"))
					elements.add(loadBusStop(line, i));
				else if(line.startsWith("stopsign"))
					elements.add(loadStopSign(line, i));
				else if(line.startsWith("stoplight"))
					elements.add(loadStopLight(line, i));
				else if(line.startsWith("crosswalk"))
					elements.add(loadCrosswalk(line, i));
				else
					System.err.println("Unexpected route element type on line " + i);
			}
		} finally {
			if(reader != null) reader.close();
		}
		return new Route(elements);
	}
	
	private static Road loadRoad(String line, int i) {
		// TODO
		return null;
	}
	
	private static BusStop loadBusStop(String line, int i) {
		// TODO
		return null;
	}
	
	private static StopSign loadStopSign(String line, int i) {
		// TODO
		return null;
	}
	
	private static StopLight loadStopLight(String line, int i) {
		// TODO
		return null;
	}
	
	private static Crosswalk loadCrosswalk(String line, int i) {
		// TODO
		return null;
	}
}
