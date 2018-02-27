import java.awt.*;
import java.util.Vector;

public class Helpers {
    public static boolean checkSlice(InputData data, Cell topLeft, Cell bottomRight) {
        int numberOfTomatos = 0;
        int numberOfMushrooms = 0;
        int x;
        int y;

        x = bottomRight.x - topLeft.x + 1;
        y = bottomRight.y - topLeft.y + 1;

        if (x * y > data.maxNumberOfCells) {
            System.out.println(topLeft.x + " " + topLeft.y + " "  + bottomRight.x + " " + bottomRight . y);
            System.out.println("max " + x + " " + y + " " + data.maxNumberOfCells);
            return false;
        }

        for (int i = topLeft.x; i <= bottomRight.x; i++) {
            for (int j = topLeft.y; j <= bottomRight.y; j++) {
                if (data.matrix[i][j].isTaken) {
                    return false;
                }

                if (data.matrix[i][j].type == false) {
                    numberOfMushrooms++;
                }
                else {
                    numberOfTomatos++;
                }
            }
        }

        System.out.println("ingred " + numberOfMushrooms + " " + numberOfTomatos);
        if (numberOfMushrooms >= data.minNumberOfEachIncredient && numberOfTomatos >= data.minNumberOfEachIncredient) {
            return true;
        }

        return false;
    }

    public static int getNumberOfMushrooms (InputData data, Cell topLeft, Cell bottomRight) {
        int number = 0;

        for (int i = topLeft.x; i < bottomRight.x; i++) {
            for (int j = topLeft.y; j < bottomRight.y; j++) {
                if (data.matrix[i][j] instanceof Mushroom) {
                    number++;
                }
            }
        }

        return number;
    }

    public static int getNumberOfTomatoes (InputData data, Cell topLeft, Cell bottomRight) {
        int number = 0;

        for (int i = topLeft.x; i < bottomRight.x; i++) {
            for (int j = topLeft.y; j < bottomRight.y; j++) {
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

    public static int sliceDimensions (Cell topLeft, Cell bottomRight) {
        int x = bottomRight.x - topLeft.x + 1;
        int y = bottomRight.y - topLeft.y + 1;
    
        return x * y;
    }
}
