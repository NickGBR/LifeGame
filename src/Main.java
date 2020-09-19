import game.*;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        Field field = new Field(50,50);
        Display display = new Display(field);
        Game game = new Game(display,2000);


        for(int i = 0; i<1000; i++){
            display.addCell((int) (Math.random()*50), (int) (Math.random()*50));
        }

        game.play();

    }
}
