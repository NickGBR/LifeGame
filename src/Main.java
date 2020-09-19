import game.*;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws InterruptedException, FileNotFoundException {





        Field field = new Field(30,30);
        Display display = new Display(field);
        Game game = new Game(display,10);
        ConsoleInterface consoleInterface = new ConsoleInterface(game);
        //consoleInterface.start();

        BufferedReader fieldStatusReader = new BufferedReader(new InputStreamReader(new FileInputStream("src/game/field.txt")));

        //String

//
//
////        for(int i = 0; i < 1000; i++){
////            display.addCell((int) (Math.random() * 50), (int) (Math.random() * 50), display.getResult());
////        }
//        display.addCell(29,29,display.getResult());
//        display.addCell(29,0,display.getResult());
//        display.addCell(29,28,display.getResult());
//
//        game.playMultithreading();
    }
}

