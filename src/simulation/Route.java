package simulation;

import java.util.List;

public class Route {

	private List<Element> elements;
	
	Route(List<Element> elements) {
		this.elements = elements;
	}
	
	public void drive(Bus bus, int n) {
		for(int i = 0; i < n; i++)
			drive(bus);
	}
	
	public void drive(Bus bus) {
		for(Element element:elements)
			element.drive(bus);
	}
}
