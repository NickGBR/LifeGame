package game.handler;

import game.Display;
import game.Game;

import java.util.concurrent.BrokenBarrierException;

public class LeftSideHandler implements Runnable{

    private Game game;
    private volatile String[][] field;
    private int xLength;
    private int yLength;
    private final String[][] cellCounter;
    private Display display;
    private int step;

    public LeftSideHandler(Game game) {
        this.game = game;
        xLength = game.getxLength();
        yLength = game.getyLength();
        cellCounter = new String[xLength][yLength];
        this.display = game.getDisplay();
        System.out.println(display.getResultString() + " multithread");
        this.field = display.getResultField();
    }

    @Override
    public void run() {
        while (step<game.getIteration()){

            waitRightThread();
            if(game.isAnimated()) {
                display.show(field);
                try {
                    Thread.sleep(160);
                } catch (InterruptedException e) {

                }
            }

            for(int x = 0; x < xLength/2; x++){
                for(int y = 0; y < yLength; y++) {
                    int cellsCounter = 0;
                    String cell = display.getCellBody();
                    String point = field[x][y];

                    if (cell.equals(field[x][game.getRightY(y + 1)])) cellsCounter++;
                    if (cell.equals(field[x][game.getRightY(y - 1)])) cellsCounter++;
                    if (cell.equals(field[game.getRightX(x + 1)][y])) cellsCounter++;
                    if (cell.equals(field[game.getRightX(x - 1)][y])) cellsCounter++;
                    if (cell.equals(field[game.getRightX(x + 1)][game.getRightY(y - 1)])) cellsCounter++;
                    if (cell.equals(field[game.getRightX(x + 1)][game.getRightY(y + 1)])) cellsCounter++;
                    if (cell.equals(field[game.getRightX(x - 1)][game.getRightY(y - 1)])) cellsCounter++;
                    if (cell.equals(field[game.getRightX(x - 1)][game.getRightY(y + 1)])) cellsCounter++;

                    cellCounter[x][y] = Integer.toString(x);

                    switch (cellsCounter){
                        case 0:
                        case 1:
                        case 4:
                        case 5:
                        case 6:
                        case 7:
                        case 8:
                            if(point.equals(cell)) {
                                display.killCell(x, y);
                            }
                            break;
                        case 2:
                            if(point.equals(cell)){
                                display.addCell(x, y);
                                break;
                            }
                            break;
                        case 3:
                            display.addCell(x, y);
                            break;
                    }
                }
            }
            game.addStep();

            waitRightThread();
            display.arrayLoad();
            step = game.getStep();
        }
    }

    private void waitRightThread() {
        try {
            game.getBarrier().await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}


