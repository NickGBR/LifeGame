import game.Display;
import game.Field;
import game.Game;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        Field field = new Field(20,20);
        Display display = new Display(field);

        for(int i = 0; i<300; i++){
            display.addCell((int) (Math.random()*20), (int) (Math.random()*20));
        }

        Game game = new Game(display,100);

        Thread thread1 = new Thread(game);
        Thread thread2 = new Thread(game);

        thread1.start();
    }
}
