package game;

public class Field {

    private int[] x;
    private int[] y;

    public Field(int length, int width) {
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
