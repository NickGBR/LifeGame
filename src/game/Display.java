package game;

public class Display {
    int i = 0;
    private String resultField = "";
    private Field field;
    private volatile String[][] result;
    private volatile String[][] futureResult;
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


    public void addCell(int coordX, int coordY, String[][] where) {

        for (int x = 0; x < xLength; x++) {
            for (int y = 0; y < yLength; y++) {

                if (x == coordX && y == coordY) {
                    where[x][y] = " O ";
                }
            }
        }
    }

    public void addCell(int coordX, int coordY) {

        for (int x = 0; x < xLength; x++) {
            for (int y = 0; y < yLength; y++) {

                if (x == coordX && y == coordY) {
                    futureResult[x][y] = " O ";
                }
            }
        }
    }


    public void killCell(int coordX, int coordY) {
        for (int x = 0; x < xLength; x++) {

            for (int y = 0; y < yLength; y++) {

                if (x == coordX && y == coordY) {
                    futureResult[x][y] = " . ";
                }
            }
        }
    }


    //Changed
    private void fillDisplay(String[][] fillingArray) {
        int i = 0;
        for (int x = 0; x < xLength; x++) {

            for (int y = 0; y < yLength; y++) {
                {
                    fillingArray[x][y] =  " . ";
                }
            }
        }
    }

    public synchronized void show(String[][] res) {
        for (int y = 0; y < yLength; y++) {
            for (int x = 0; x < xLength; x++) {
                resultField = resultField + res[x][y];
            }
            resultField = resultField + "\n";
        }
        System.out.print(resultField);
        resultField = "";
    }

    public String getResultString(String[][] res){
        String resultField = "";
        for (int y = 0; y < yLength; y++) {
            for (int x = 0; x < xLength; x++) {
                resultField = resultField + res[x][y];
            }
            resultField = resultField + "\n";
        }
        return resultField;
    }


    public void showCounts(String[][] strings) {
        for (int y = 0; y < this.yLength; y++) {
            System.out.println();
            for (int x = 0; x < this.xLength; x++) {
                System.out.print(strings[x][y] + " ");
            }
        }
    }


    public String[][] getResult() {
        return result;
    }

    int getYLength() {
        return yLength;
    }

    int getXLength() {
        return xLength;
    }

    public synchronized void arrayMultithreadingLoad() {
        if (Thread.currentThread().getName().equals("right")) {

            for (int x = xLength/2-1; x < xLength; x++) {
                for (int y = 0; y < yLength; y++) {
                    result[x][y] = futureResult[x][y];
                }
            }

        }

        if (Thread.currentThread().getName().equals("left")) {

            for (int x = 0; x < xLength/2+1; x++) {
                for (int y = 0; y < yLength; y++) {
                    result[x][y] = futureResult[x][y];
                }
            }
        }
        fillDisplay(futureResult);
    }

    void arrayLoad() {
            for (int x = 0; x < xLength; x++) {
                for (int y = 0; y < yLength; y++) {
                    result[x][y] = futureResult[x][y];
                }
            }
        fillDisplay(futureResult);

    }

    void generateCells(int worldOccupancyRate){
        for(int i = 0; i < worldOccupancyRate*(this.xLength+this.yLength); i++){
            this.addCell((int) (Math.random() * this.xLength), (int) (Math.random() * this.yLength), this.getResult());
        }
    }

}
