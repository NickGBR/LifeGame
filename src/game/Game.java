package game;

import game.handler.LeftSideHandler;
import game.handler.RightSideHandler;

import java.util.concurrent.CyclicBarrier;

public class Game{
    private Display display;
    private int cellsCounter;
    private int iteration;
    private volatile int step=0;
    private int yLength;
    private int xLength;
    private boolean animated = false;

    private String status[][];
    private String cellCounter[][];
    private CyclicBarrier barrier;

    public Game(Display display, int iteration) {
        this.barrier = new CyclicBarrier(2);
        this.iteration = iteration;
        this.display = display;
        this.status = display.getResultField();
        this.xLength = display.getXLength();
        this.yLength = display.getYLength();
        this.cellCounter = new String[xLength][yLength];
    }

    public Game(Display display, int iteration, boolean animated) {
        this.barrier = new CyclicBarrier(2);
        this.iteration = iteration;
        this.display = display;
        this.status = display.getResultField();
        this.xLength = display.getXLength();
        this.yLength = display.getYLength();
        this.cellCounter = new String[xLength][yLength];
        this.animated = animated;
    }

    public void play() throws InterruptedException {
        for(int iteration = 0; iteration<this.iteration; iteration++){
            if(this.isAnimated()) {
                Thread.sleep(160);
                display.show(status);
            }
            for(int x = 0; x<xLength;x++){
                for(int y = 0; y<yLength;y++) {
                    cellsCounter = 0;
                    String point = status[x][y];
                    String cell = display.getCellBody();

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
                        case 2: if(point.equals(cell)){
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
    }

    public void playMultithreading() throws InterruptedException {
        Thread leftSideHandler = new Thread(new LeftSideHandler(this));
        Thread rightSideHandler = new Thread(new RightSideHandler(this));
        rightSideHandler.setName("right");
        leftSideHandler.setName("left");
        rightSideHandler.start();
        leftSideHandler.start();
        rightSideHandler.join();
        leftSideHandler.join();
    }

    public void setAnimated(boolean animated) {
        this.animated = animated;
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

    public boolean isAnimated() {
        return animated;
    }

    public CyclicBarrier getBarrier() {
        return barrier;
    }

    public void setDisplay(Display display) {
        this.display = display;
    }
}
