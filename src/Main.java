import game.*;
import game.exceptions.IncorrectFieldException;

import java.io.*;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws InterruptedException, IOException, IncorrectFieldException {
        //ConsoleInterface consoleInterface = new ConsoleInterface();

        Field field = new Field(5,5);
        Display display =  new Display(field);
        Game game = new Game(display, 9);
        display.addCell(0,2,display.getResult());
        display.addCell(1,2,display.getResult());
        display.addCell(4,2,display.getResult());
        game.play();

    }
}

