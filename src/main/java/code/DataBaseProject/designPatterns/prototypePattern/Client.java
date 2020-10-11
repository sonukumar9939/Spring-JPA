package code.DataBaseProject.designPatterns.prototypePattern;

public class Client {

    public static void main(String[] args) {
        ShapeCache.loadCache();

        Shape clonedShape = (Shape) ShapeCache.getShape("1");
        System.out.println("Shape : " + clonedShape.getType());

        Shape clonedShape2 = (Shape) ShapeCache.getShape("2");
        System.out.println("Shape : " + clonedShape2.getType());
        
        System.out.println(clonedShape==clonedShape2);

    }

}
