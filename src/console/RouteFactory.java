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

	/**
	 * Loads a route from a route file at the specified directory.
	 * 
	 * @param file
	 * 		the route file's directory
	 * @return
	 * 		the route object
	 * @throws FileNotFoundException
	 * 		thrown if the file isn't found
	 * @throws IOException
	 */
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
	
	// ====================================================
	// Loading Road elements
	// ======================
	
	private static Road loadRoad(String[] line, int i) {
		double speed = 0.0d, length = 0.0d, elevation = 0.0d;
		try {
			if(line.length < 4) throw new NumberFormatException();
			speed = Double.parseDouble(line[1]) * Constants.MPH_TO_METERS_PER_S;
			length = Double.parseDouble(line[2]);
			elevation  = Double.parseDouble(line[3]);
		} catch(NumberFormatException e) {
			System.err.println("Imporperly formatted Road on line " + i);
			System.exit(-1);
		}
		return new Road(speed, length, elevation);
	}
	
	// ====================================================
	// Loading BusStop elements
	// ======================
	
	private static BusStop loadBusStop(String[] line, int i) {
		int congestion = 0;
		try {
			if(line.length < 2) throw new NumberFormatException();
			congestion = Integer.parseInt(line[1]);
		} catch(NumberFormatException e) {
			System.err.println("Imporperly formatted BusStop on line " + i);
			System.exit(-1);
		}
		return new BusStop(busStopWait(congestion));
	}
	
	private static double busStopWait(int congestion) {
		// TODO
		return congestion;
	}
	
	// ====================================================
	// Loading StopSign elements
	// ======================
	
	private static StopSign loadStopSign(String[] line, int i) {
		int congestion = 0;
		try {
			if(line.length < 2) throw new NumberFormatException();
			congestion = Integer.parseInt(line[1]);
		} catch(NumberFormatException e) {
			System.err.println("Imporperly formatted BusStop on line " + i);
			System.exit(-1);
		}
		return new StopSign(stopSignWait(congestion));
	}
	
	private static double stopSignWait(int congestion) {
		// TODO
		return congestion;
	}
	
	// ====================================================
	// Loading StopLight elements
	// ======================
	
	private static StopLight loadStopLight(String[] line, int i) {
		int congestion = 0;
		boolean turn = false;
		try {
			if(line.length < 3) throw new NumberFormatException();
			congestion = Integer.parseInt(line[1]);
			turn = Boolean.parseBoolean(line[2]);
		} catch(IllegalArgumentException e) {
			System.err.println("Imporperly formatted StopLight on line " + i);
			System.exit(-1);
		}
		return new StopLight(stopLightWait(congestion), turn);
	}
	
	private static double stopLightWait(int congestion) {
		// TODO
		return congestion;
	}
	
	// ====================================================
	// Loading Crosswalk elements
	// ======================
	
	private static Crosswalk loadCrosswalk(String[] line, int i) {
		int congestion = 0;
		try {
			if(line.length < 2) throw new NumberFormatException();
			congestion = Integer.parseInt(line[1]);
		} catch(NumberFormatException e) {
			System.err.println("Imporperly formatted Crosswalk on line " + i);
			System.exit(-1);
		}
		return new Crosswalk(crosswalkWait(congestion), crosswalkStop(congestion));
	}
	
	private static double crosswalkWait(int congestion) {
		// TODO
		return congestion;
	}
	
	private static double crosswalkStop(int congestion) {
		// TODO
		return congestion;
	}
}
