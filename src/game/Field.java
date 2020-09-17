package game;

public class Field {
    private  int length;
    private  int width;

    private int[] x = new int[length];
    private int[] y = new int[width];

    public Field(int length, int width) {
        this.length = length;
        this.width = width;
        this.x = new int[length];
        this.y = new int[width];
    }

    public int[] getX() {
        return x;
    }

    public int[] getY() {
        return y;
    }
}
