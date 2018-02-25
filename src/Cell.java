import java.awt.*;

public abstract class Cell {
    public Point position;
    public boolean isTaken;

    public Cell (int x, int y) {
        position = new Point(x, y);
    }
}