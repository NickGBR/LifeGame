package game;

import java.util.concurrent.atomic.AtomicReferenceArray;

public class LivesGiver implements Runnable {

    private Game game;
    private String[][] field;
    private int xLength;
    private int yLength;
    private final String[][] cellCounter;
    private Display display;
    private int step;

    LivesGiver(Game game) {
        this.game = game;
        field = game.getStatus();
        xLength = game.getxLength();
        yLength = game.getyLength();
        cellCounter = new String[xLength][yLength];
        this.display = game.getDisplay();
    }

    @Override
    public void run() {
        while (step<game.getIteration()){

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {

            }
            System. out. print("\033[H\033[2J");
            display.show(field);

            for(int x = 0; x<xLength;x++){
                for(int y = 0; y<yLength;y++) {
                    int cellsCounter = 0;
                    String cell = " O ";
                    String point = field[x][y];
                    if (cell.equals(field[x][game.getRightY(y+1)])) cellsCounter++;
                    if (cell.equals(field[x][game.getRightY(y-1)])) cellsCounter++;
                    if (cell.equals(field[game.getRightX(x+1)][y])) cellsCounter++;
                    if (cell.equals(field[game.getRightX(x-1)][y])) cellsCounter++;
                    if (cell.equals(field[game.getRightX(x+1)][game.getRightY(y-1)])) cellsCounter++;
                    if (cell.equals(field[game.getRightX(x+1)][game.getRightY(y+1)])) cellsCounter++;
                    if (cell.equals(field[game.getRightX(x-1)][game.getRightY(y-1)])) cellsCounter++;
                    if (cell.equals(field[game.getRightX(x-1)][game.getRightY(y+1)])) cellsCounter++;

                    cellCounter[x][y] = Integer.toString(x);

                    switch (cellsCounter){
                        case 2: if(point.equals(" O ")){
                            display.addCell(x, y);
                        }
                        case 3:
                            display.addCell(x, y);
                            cellsCounter = 0;
                            break;
                    }
                }
            }
            display.arrayLoad();
            game.addStep();
            step = game.getStep();
            System.out.println("Giver = " + game.getStep());

        }
    }
}

