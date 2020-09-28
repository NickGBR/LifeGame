package game.handler;

import game.Field;
import game.Game;

import java.util.concurrent.BrokenBarrierException;

public class RightSideHandler implements Runnable {

    private Game game;
    private String[][] resultField;
    private int xLength;
    private int yLength;
    private final String[][] cellCounter;
    private Field field;
    private int step;

    public RightSideHandler(Game game) {
        this.game = game;
        xLength = game.getxLength();
        yLength = game.getyLength();
        cellCounter = new String[xLength][yLength];
        this.field = game.getField();
        this.resultField = field.getResultField();
    }

    @Override
    public void run() {
        while (step<game.getIteration()){

            waitLeftThread();

            for(int x = xLength/2; x < xLength; x++){
                for(int y = 0; y < yLength; y++) {

                    int cellsCounter = 0;
                    String cell = field.getCellBody();
                    String point = resultField[x][y];

                    if (cell.equals(resultField[x][game.getRightY(y + 1)])) cellsCounter++;
                    if (cell.equals(resultField[x][game.getRightY(y - 1)])) cellsCounter++;
                    if (cell.equals(resultField[game.getRightX(x + 1)][y])) cellsCounter++;
                    if (cell.equals(resultField[game.getRightX(x - 1)][y])) cellsCounter++;
                    if (cell.equals(resultField[game.getRightX(x + 1)][game.getRightY(y - 1)])) cellsCounter++;
                    if (cell.equals(resultField[game.getRightX(x + 1)][game.getRightY(y + 1)])) cellsCounter++;
                    if (cell.equals(resultField[game.getRightX(x - 1)][game.getRightY(y - 1)])) cellsCounter++;
                    if (cell.equals(resultField[game.getRightX(x - 1)][game.getRightY(y + 1)])) cellsCounter++;

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
                                field.killCell(x, y);
                            }
                            break;
                        case 2: if(point.equals(cell)){
                            field.addCell(x, y);
                            break;
                        }
                            break;
                        case 3:
                            field.addCell(x, y);
                            break;
                    }
                }
            }

            waitLeftThread();

            step = game.getStep();
        }
    }

    private void waitLeftThread() {
        try {
            game.getBarrier().await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}
