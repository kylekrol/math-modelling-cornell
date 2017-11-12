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
				System.out.println(bus.gasUsage());
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

/*

original
140.27116460546762
280.57735698870727
420.8552577052848
561.1385473107515
701.4380035828823
841.730723743903
982.0140133493697
1122.2865251770602
1262.6102314491866
1402.8692710546568

no idle
139.64066460557171
279.2813292111434
418.921993816715
558.5626584222869
698.2033230278587
837.8439876334305
977.4846522390023
1117.125316844574
1256.7659814501458
1396.4066460557176

*/

