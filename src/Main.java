import game.Display;
import game.Field;

public class Main {

    public static void main(String[] args) {

        String seed = "7";

        System.out.println("Hello World!");

        Field field = new Field(10,10);
        Display display = new Display(field);

        display.addPoint(5,5,seed);
        display.addPoint(6,6,seed);
        display.addPoint(7,7,seed);
        display.removePoint(6,6);
        display.show();
    }
}
