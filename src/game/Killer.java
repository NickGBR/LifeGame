package game;

public class Killer implements Runnable {

    private Game game;
    private String[][] field;
    private int xLength;
    private int yLength;
    private String[][]cellCounter;
    private Display display;
    private int step;

    Killer(Game game){
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

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {

            }

            for(int x = 0; x<xLength;x++){
                for(int y = 0; y<yLength;y++) {
                    int cellsCounter = 0;
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
                        case 1:
                        case 4:
                        case 5:
                        case 6:
                        case 7:
                        case 8:
                            display.killCell(x, y);
                            break;
                    }
                }
            }
            display.arrayLoad();
            game.addStep();
            step = game.getStep();
            System.out.println("killer = " + game.getStep());
        }

    }
}

