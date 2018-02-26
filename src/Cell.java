import java.awt.*;

public abstract class Cell {
    public int x;
    public int y;
    public boolean isTaken;
    public boolean type;

    public Cell (int x, int y) {
        this.x = x;
        this.y = y;
        this.isTaken = false;
    }
}