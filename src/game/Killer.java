package game;

public class Killer implements Runnable {

    private Game game;
    private String[][] field;
    private int xLength;
    private int yLength;
    private int cellsCounter;
    private String[][]cellCounter;
    private Display display;
    int step;

    public Killer(Game game) throws InterruptedException {
        //Thread.sleep();
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
            for(int x = 0; x<xLength;x++){
                for(int y = 0; y<yLength;y++) {

                    String cell = " O ";
                    if (cell.equals(field[x][game.getRightY(y+1)])) cellsCounter++;
                    if (cell.equals(field[x][game.getRightY(y-1)])) cellsCounter++;
                    if (cell.equals(field[game.getRightX(x+1)][y])) cellsCounter++;
                    if (cell.equals(field[game.getRightX(x-1)][y])) cellsCounter++;
                    if (cell.equals(field[game.getRightX(x+1)][game.getRightY(y-1)])) cellsCounter++;
                    if (cell.equals(field[game.getRightX(x+1)][game.getRightY(y+1)])) cellsCounter++;
                    if (cell.equals(field[game.getRightX(x-1)][game.getRightY(y-1)])) cellsCounter++;
                    if (cell.equals(field[game.getRightX(x-1)][game.getRightY(y+1)])) cellsCounter++;

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
                        default:cellsCounter = 0;
                            break;
                    }
                }
            }
            game.addStep();
            step = game.getStep();
        }

    }
}

