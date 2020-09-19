package game;

public class Display {
    int i = 0;
    private String resultField="";
    private Field field;
    private String[][] result;
    private String[][] futureResult;
    private int yLength;
    private int xLength;

    public Display(Field field) {
        this.field = field;
        this.yLength = field.getY().length;
        this.xLength = field.getX().length;
        this.result = new String[xLength][yLength];
        this.futureResult = new String[xLength][yLength];
        fillDisplay(result);
        fillDisplay(futureResult);
    }



    public void addCell(int coordX, int coordY, String[][] where){

        for(int x = 0;x<xLength;x++){
            for(int y = 0;y<yLength;y++){

                if(x == coordX && y == coordY){
                    where[x][y] = " O ";
                }
            }
        }
    }

    public void addCell(int coordX, int coordY){

        for(int x = 0;x<xLength;x++){
            for(int y = 0;y<yLength;y++){

                if(x == coordX && y == coordY){
                    futureResult[x][y] = " O ";
                }
            }
        }
    }


    public void killCell(int coordX, int coordY){
        for(int x = 0;x<xLength;x++){

            for(int y = 0;y<yLength;y++){

                if(x == coordX && y == coordY){
                    futureResult[x][y] = " . ";
                }
            }
        }
    }

    private void fillDisplay(String[][]  fillingArray){
        for(int x = 0;x<xLength;x++){

            for(int y = 0;y<yLength;y++){ {
                fillingArray[x][y] = " . ";
            }
            }
        }
    }

    public void show(String[][] res) {
        for(int y = 0 ; y<yLength; y++){
            System.out.println();
            for(int x = 0; x<yLength; x++){
                resultField = resultField + res[x][y];
            }
            resultField =resultField +"\n";
        }
        System.out.print(resultField);
        resultField = "";
    }

    public static void showCounts(String[][] strings){
        for(int y = 0 ; y<strings.length; y++){
            System.out.println();
            for(int x = 0; x<strings.length; x++){
                System.out.print(strings[x][y] + " ");
            }
        }

    }


    public String[][] getResult() {
        return result;
    }

    public String[][] getFutureResult() {
        return futureResult;
    }

    int getYLength() {
        return yLength;
    }

    int getXLength() {
        return xLength;
    }

    public void arrayLoad(){
        fillDisplay(result);
        for(int x = 0; x < xLength; x++){
            for(int y = 0; y < yLength; y++){
                result[x][y] = futureResult[x][y];
            }
        }
        fillDisplay(futureResult);
    }
}
