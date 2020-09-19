package game;

import game.handler.LeftSideHandler;
import game.handler.RightSideHandler;

public class Game{
    private Display display;
    private int cellsCounter;
    private int iteration;
    private volatile int step=0;
    private int yLength;
    private int xLength;

    private String status[][];
    private String cellCounter[][];

    public Game(Display display, int iteration) {
        this.iteration = iteration;
        this.display = display;
        this.status = display.getResult();
        this.xLength = display.getXLength();
        this.yLength = display.getYLength();
        this.cellCounter = new String[xLength][yLength];
    }

    public void play() throws InterruptedException {
        for(int iteration = 0; iteration<this.iteration; iteration++){
            Thread.sleep(1000);
            System. out. print("\033[H\033[2J");
            display.show(status);
            for(int x = 0; x<xLength;x++){
                for(int y = 0; y<yLength;y++) {
                    cellsCounter = 0;
                    String point = status[x][y];
                    String cell = " O ";
                    if (cell.equals(status[x][getRightY(y+1)])) cellsCounter++;
                    if (cell.equals(status[x][getRightY(y-1)])) cellsCounter++;
                    if (cell.equals(status[getRightX(x+1)][y])) cellsCounter++;
                    if (cell.equals(status[getRightX(x-1)][y])) cellsCounter++;
                    if (cell.equals(status[getRightX(x+1)][getRightY(y-1)])) cellsCounter++;
                    if (cell.equals(status[getRightX(x+1)][getRightY(y+1)])) cellsCounter++;
                    if (cell.equals(status[getRightX(x-1)][getRightY(y-1)])) cellsCounter++;
                    if (cell.equals(status[getRightX(x-1)][getRightY(y+1)])) cellsCounter++;

                    cellCounter[x][y] = Integer.toString(cellsCounter);

                    switch (cellsCounter){
                        case 0:
                        case 1:
                        case 4:
                        case 5:
                        case 6:
                        case 7:
                        case 8:
                            if(point.equals(cell)){
                            display.killCell(x, y);}
                            break;
                        case 2: if(point.equals(" O ")){
                            display.addCell(x, y);
                        }
                            break;
                        case 3:
                            display.addCell(x, y);
                            break;
                    }
                }
            }
            display.arrayLoad();

        }
        Display.showCounts(cellCounter);
    }

    public void playMultithreading() throws InterruptedException {
        Thread leftSideHandler = new Thread(new LeftSideHandler(this));
        Thread rightSideHandler = new Thread(new RightSideHandler(this));
        rightSideHandler.setName("right");
        leftSideHandler.setName("left");
        rightSideHandler.start();
        leftSideHandler.start();
    }


    public int getRightX(int x) {
        int correctedX = x;
        if (x < 0) correctedX = xLength - 1;
        else if (x > xLength-1) correctedX = 0;
        return correctedX;
    }

    public int getRightY(int y) {
        int correctedY = y;
        if (y < 0) correctedY = yLength - 1;
        else if (y > yLength-1) correctedY = 0;
        return correctedY;
    }

    public int getIteration() {
        return iteration;
    }

    public String[][] getStatus() {
        return status;
    }

    public int getyLength() {
        return yLength;
    }

    public int getxLength() {
        return xLength;
    }

    public Display getDisplay() {
        return display;
    }

    public int getStep() {
        return step;
    }

    public void addStep() {
        this.step++;
    }
}
