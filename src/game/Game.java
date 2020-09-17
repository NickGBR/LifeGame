package game;

public class Game implements Runnable{
    private Display display;
    private int cellsCounter;
    private int iteration;
    private int yLength;
    private int xLength;

    private String status[][];
    private String cellCounter[][];

    public Game(Display display, int iteration) {
        this.iteration = iteration;
        this.display = display;
        this.status = display.getResult();
        this.xLength = display.getxLength();
        this.yLength = display.getyLength();
        this.cellCounter = new String[xLength][yLength];
    }

    public void play(){
        for(int iteration = 0; iteration<this.iteration; iteration++){
            for(int x = 0; x<xLength;x++){
                display.show();
                //Thread.sleep(100);
                for(int y = 0; y<yLength;y++) {
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
                        //case 2:
                        case 1:
                        case 4:
                        case 5:
                        case 6:
                        case 7:
                        case 8:
                            cellsCounter = 0;
                            display.killCell(x, y);
                            break;
                        case 3:
                            display.addCell(x, y);
                            cellsCounter = 0;
                        default:cellsCounter = 0;
                    }
                }
            }
        }
        Display.show(cellCounter);
    }


    private int getRightX(int x) {
        int correctedX = x;
        if (x < 0) correctedX = xLength - 1;
        else if (x > xLength-1) correctedX = 0;
        return correctedX;
    }

    private int getRightY(int y) {
        int correctedY = y;
        if (y < 0) correctedY = yLength - 1;
        else if (y > yLength-1) correctedY = 0;
        return correctedY;
    }

    public String[][] getStatus() {
        return status;
    }

    @Override
    public void run() {
        play();
    }
}
