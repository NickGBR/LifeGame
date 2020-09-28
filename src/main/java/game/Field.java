package game;

public class Field {

    private String resultField = "";
    private volatile String[][] result;
    private volatile String[][] futureResult;
    private int yLength;
    private int xLength;
    private static String cellBody = " O ";
    private static String deadCellBody = "   ";

    public boolean isAlive(int x, int y){
        return result[x][y].equals(cellBody);
    }

    public Field(int xLength, int yLength){
        this.yLength = yLength;
        this.xLength = xLength;
        this.result = new String[xLength][yLength];
        this.futureResult = new String[xLength][yLength];
        reloadDisplay();
    }

    public void reloadDisplay(){
        fillDisplay(result);
        fillDisplay(futureResult);
    }

    public void addCell(int coordX, int coordY, String[][] where) {

        for (int x = 0; x < xLength; x++) {
            for (int y = 0; y < yLength; y++) {

                if (x == coordX && y == coordY) {
                    where[x][y] = cellBody;
                }
            }
        }
    }

    public void addCell(int coordX, int coordY) {

        for (int x = 0; x < xLength; x++) {
            for (int y = 0; y < yLength; y++) {

                if (x == coordX && y == coordY) {
                    futureResult[x][y] = cellBody;
                }
            }
        }
    }

    public void killCell(int coordX, int coordY) {
        for (int x = 0; x < xLength; x++) {

            for (int y = 0; y < yLength; y++) {

                if (x == coordX && y == coordY) {
                    futureResult[x][y] = deadCellBody;
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
                    fillingArray[x][y] = deadCellBody;
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
        System. out. print("\033[H\033[2J");
        System.out.print(resultField);
        resultField = "";
    }

    public String getString(String[][] res){
        String resultField = "";
        for (int y = 0; y < yLength; y++) {
            for (int x = 0; x < xLength; x++) {
                resultField = resultField + res[x][y];
            }
            resultField = resultField + "\n";
        }
        return resultField;
    }

    public String getResultString(){
        String resultField = "";
        for (int y = 0; y < yLength; y++) {
            for (int x = 0; x < xLength; x++) {
                resultField = resultField + result[x][y];
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

    public void arrayLoad() {
        for (int x = 0; x < xLength; x++) {
            for (int y = 0; y < yLength; y++) {
                result[x][y] = futureResult[x][y];
            }
        }
        fillDisplay(futureResult);

    }

    void generateCells(int worldOccupancyRate){
        for(int i = 0; i < worldOccupancyRate*(this.xLength+this.yLength); i++){
            this.addCell((int) (Math.random() * this.xLength), (int) (Math.random() * this.yLength), this.getResultField());
        }
    }

    public String getCellBody() {
        return cellBody;
    }

    public String[][] getResultField() {
        return result;
    }

    int getYLength() {
        return yLength;
    }

    int getXLength() {
        return xLength;
    }

    public void setDeadCellBody(String deadCellBody) {
        Field.deadCellBody = deadCellBody;
    }
}
