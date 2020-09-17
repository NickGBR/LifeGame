package game;

import java.util.Arrays;

public class Display {
    int i = 0;
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



    public void addCell(int coordX, int coordY){

        for(int x = 0;x<xLength;x++){
            for(int y = 0;y<yLength;y++){

                if(x == coordX && y == coordY){
                    result[x][y] = " O ";
                }
            }
        }
    }

    public void killCell(int coordX, int coordY){
        for(int x = 0;x<xLength;x++){

            for(int y = 0;y<yLength;y++){

                if(x == coordX && y == coordY){
                    result[x][y] = " . ";
                }
            }
        }
    }

    private void fillDisplay(){
        for(int x = 0;x<xLength;x++){

            for(int y = 0;y<yLength;y++){ {
                result[x][y] = " . ";
            }
            }
        }
    }

    public void show(){
        for(int y = 0 ; y<yLength; y++){
            System.out.println();
            for(int x = 0; x<yLength; x++){
                System.out.print(result[x][y]);
            }
        }
        System.out.println("");
    }

    public static void show(String[][] strings){
        for(int y = 0 ; y<strings.length; y++){
            System.out.println();
            for(int x = 0; x<strings.length; x++){
                System.out.print(strings[x][y] + " ");
            }
        }

    }


    String[][] getResult() {
        return result;
    }

    int getyLength() {
        return yLength;
    }

    int getxLength() {
        return xLength;
    }
}
