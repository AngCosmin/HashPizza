import java.awt.*;
import java.util.Vector;

public class Helpers {
    public static boolean checkSlice(InputData data, Cell topLeft, Cell bottomRight) {
        int numberOfTomatos = 0;
        int numberOfMushrooms = 0;

        int x = bottomRight.position.x - topLeft.position.x + 1;
        int y = bottomRight.position.y - topLeft.position.y + 1;

        if (x * y > data.maxNumberOfCells) {
            return false;
        }

        for (int i = topLeft.position.x; i < bottomRight.position.x; i++) {
            for (int j = topLeft.position.y; j < bottomRight.position.y; j++) {
                if (data.matrix[i][j].isTaken) {
                    return false;
                }

                if (data.matrix[i][j] instanceof Mushroom) {
                    numberOfMushrooms++;
                }
                else {
                    numberOfTomatos++;
                }
            }
        }

        if (numberOfMushrooms >= data.minNumberOfEachIncredient && numberOfTomatos >= data.minNumberOfEachIncredient) {
            return true;
        }

        return false;
    }

    public static int getNumberOfMushrooms (InputData data, Cell topLeft, Cell bottomRight) {
        int number = 0;

        for (int i = topLeft.position.x; i < bottomRight.position.x; i++) {
            for (int j = topLeft.position.y; j < bottomRight.position.y; j++) {
                if (data.matrix[i][j] instanceof Mushroom) {
                     number++;
                }
            }
        }

        return number;
    }

    public static int getNumberOfTomatoes (InputData data, Cell topLeft, Cell bottomRight) {
        int number = 0;

        for (int i = topLeft.position.x; i < bottomRight.position.x; i++) {
            for (int j = topLeft.position.y; j < bottomRight.position.y; j++) {
                if (data.matrix[i][j] instanceof Tomato) {
                    number++;
                }
            }
        }

        return number;
    }

    public static Vector<Point> getDirectionsVector() {
        Vector<Point> directions = new Vector<>();

//        directions.add(new Point(-1, -1));
//        directions.add(new Point(-1, 0));
//        directions.add(new Point(-1, 1));
        directions.add(new Point(0, 1));
        directions.add(new Point(1, 1));
        directions.add(new Point(1, 0));
        directions.add(new Point(1, -1));
        directions.add(new Point(0, -1));

        return directions;
    }

    public static void markSlice(InputData data, Cell topLeft, Cell bottomRight) {
        for (int i = topLeft.position.x; i < bottomRight.position.x; i++) {
            for (int j = topLeft.position.y; j < bottomRight.position.y; j++) {
                data.matrix[i][j].isTaken = true;
            }
        }
    }

    public static Cell getFirstNotTakenCell (InputData data) {
        for (short i = 0; i < data.rows; i++) {
            for (short j = 0; j < data.columns; j++) {
                if (!data.matrix[i][j].isTaken) {
                    Vector<Point> directions = Helpers.getDirectionsVector();

                    for (int k = 0; k < 5; k++) {
                        int x = directions.elementAt(k).x;
                        int y = directions.elementAt(k).y;

                        if (!data.matrix[x][y].isTaken) {
                            return data.matrix[x][y];
                        }
                    }

                }
            }
        }

        return null;
    }
}
