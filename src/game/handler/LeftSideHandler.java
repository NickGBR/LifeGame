package game.handler;

import game.Display;
import game.Game;

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
            field = game.getStatus();
            xLength = game.getxLength();
            yLength = game.getyLength();
            cellCounter = new String[xLength][yLength];
            this.display = game.getDisplay();
        }

        @Override
        public void run() {
            while (step<game.getIteration()*2){

                try {
                    Thread.sleep(250);
                } catch (InterruptedException e) {

                }
                System. out. print("\033[H\033[2J");
                display.show(field);

                for(int x = 0; x < xLength/2+1; x++){
                    for(int y = 0; y < yLength; y++) {

                        int cellsCounter = 0;
                        String cell = " O ";
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
                display.arrayMultithreadingLoad();
                game.addStep();
                step = game.getStep();
            }
        }
    }


