package console;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import simulation.Route;
import simulation.elements.BusStop;
import simulation.elements.Crosswalk;
import simulation.elements.Road;
import simulation.elements.StopLight;
import simulation.elements.StopSign;

/** Class responsible for loading Route files into memory */
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
		Route route = new Route();
		BufferedReader reader = null;
		try {
			int i = 0;
			String line;
			reader = new BufferedReader(new FileReader(file));
			while((line = reader.readLine()) != null) {
				line = line.toLowerCase();
				i++;
				if(line.startsWith("rd"))
					route.add(loadRoad(line.split(" "), i));
				else if(line.startsWith("bs"))
					route.add(loadBusStop(line.split(" "), i));
				else if(line.startsWith("ss"))
					route.add(loadStopSign(line.split(" "), i));
				else if(line.startsWith("sl"))
					route.add(loadStopLight(line.split(" "), i));
				else if(line.startsWith("cw"))
					route.add(loadCrosswalk(line.split(" "), i));
				else if(!line.startsWith("//"))
					System.err.println("Unexpected route element type on line " + i);
			}
		} finally {
			if(reader != null) reader.close();
		}
		return route;
	}
	
	/**
	 * Loads a Road object from the split line of a file. Takes in the current
	 * line number for error message purposes.
	 * 
	 * @param line
	 * 		current file line split on a " "
	 * @param i
	 * 		the current line number
	 * @return
	 * 		the parsed Road object
	 */
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
	
	/**
	 * Loads a BusStop object from the split line of a file. Takes in the
	 * current line number for error message purposes.
	 * 
	 * @param line
	 * 		current file line split on a " "
	 * @param i
	 * 		the current line number
	 * @return
	 * 		the parsed BusStop object
	 */
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
	
	/** Loads a crosswalk wait time for the given congestion level */
	private static double busStopWait(int congestion) {
		if(congestion == 1)
			return 30.0d;
		else if(congestion == 2)
			return 45.0d;
		else if(congestion == 3)
			return 90.0d;
		System.err.println("Invalid congestion argument");
		System.exit(-1);
		return 0.0d;
	}
	
	/**
	 * Loads a StopSign object from the split line of a file. Takes in the
	 * current line number for error message purposes.
	 * 
	 * @param line
	 * 		current file line split on a " "
	 * @param i
	 * 		the current line number
	 * @return
	 * 		the parsed StopSign object
	 */
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
	
	/** Loads a stop sign wait time for the given congestion level */
	private static double stopSignWait(int congestion) {
		if(congestion == 1)
			return 5.0d;
		else if(congestion == 2)
			return 20.0d;
		else if(congestion == 3)
			return 40.0d;
		System.err.println("Invalid congestion argument");
		System.exit(-1);
		return 0.0d;
	}
	
	/**
	 * Loads a StopLight object from the split line of a file. Takes in the
	 * current line number for error message purposes.
	 * 
	 * @param line
	 * 		current file line split on a " "
	 * @param i
	 * 		the current line number
	 * @return
	 * 		the parsed StopLight object
	 */
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
	
	/** Loads a stop light wait time for the given congestion level */
	private static double stopLightWait(int congestion) {
		if(congestion == 1)
			return 20.0d;
		else if(congestion == 2)
			return 30.0d;
		else if(congestion == 3)
			return 40.0d;
		System.err.println("Invalid congestion argument");
		System.exit(-1);
		return 0.0d;
	}
	
	/**
	 * Loads a Crosswalk object from the split line of a file. Takes in the
	 * current line number for error message purposes.
	 * 
	 * @param line
	 * 		current file line split on a " "
	 * @param i
	 * 		the current line number
	 * @return
	 * 		the parsed Crosswalk object
	 */
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
	
	/** Loads a crosswalk wait time for the given congestion level */
	private static double crosswalkWait(int congestion) {
		if(congestion == 1)
			return 10.0d;
		else if(congestion == 2)
			return 20.0d;
		else if(congestion == 3)
			return 45.0d;
		System.err.println("Invalid congestion argument");
		System.exit(-1);
		return 0.0d;
	}
	
	/** Loads a crosswalk stop chance for the given congestion level */
	private static double crosswalkStop(int congestion) {
		if(congestion == 1)
			return 0.05d;
		else if(congestion == 2)
			return 0.20d;
		else if(congestion == 3)
			return 0.60d;
		System.err.println("Invalid congestion argument");
		System.exit(-1);
		return 0.0d;
	}
}
