package game;

import java.util.Arrays;

public class Display {
    private Field field;
    private String[][] result;
    private int yLength;
    private int xLength;

    public Display(Field field) {
        this.field = field;
        this.yLength = field.getY().length;
        this.xLength = field.getX().length;
        this.result = new String[xLength][yLength];
        fillDisplay();
    }



    public void addPoint(int coordX, int coordY,String seedName){
        for(int x = 0;x<xLength;x++){
            for(int y = 0;y<yLength;y++){

                if(x == coordX-1 && y == coordY-1){
                    result[x][y] = " " + seedName + " ";
                }
            }
        }
    }

    public void removePoint(int coordX, int coordY){
        for(int x = 0;x<xLength;x++){

            for(int y = 0;y<yLength;y++){

                if(x == coordX-1 && y == coordY-1){
                    result[x][y] = " * ";
                }
            }
        }
    }

    private void fillDisplay(){
        for(int x = 0;x<xLength;x++){

            for(int y = 0;y<yLength;y++){ {
                    result[x][y] = " * ";
                }
            }
        }
    }

    public void show(){
        for(int x = 0; x<xLength; x++){
            System.out.println();
            for(int y = 0; y<yLength; y++){
                System.out.print(result[x][y]);
            }
        }
    }

}
