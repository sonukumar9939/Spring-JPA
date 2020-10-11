package code.DataBaseProject.designPatterns.prototypePattern;

import java.util.HashMap;
import java.util.Map;

public class ShapeCache {
	
	private static Map<String,Shape> shapeMap = new HashMap<String, Shape>();
	
	public static Shape getShape(String id) {
		Shape cachedShape= shapeMap.get(id);
		return (Shape) cachedShape.clone();
	}

	public static void loadCache() {
        Circle circle = new Circle();
        shapeMap.put("1",circle);

        Rectangle rectangle = new Rectangle();
        shapeMap.put("2", rectangle);
    }
}
