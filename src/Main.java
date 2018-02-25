import java.awt.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        InputData in = new InputData();
        in.read("small.in");
        ArrayList<OutputData> out = new ArrayList<>();

        Queue<Cell> neighbors = new LinkedList<>();
        Cell topLeft = in.matrix[0][0];
        Cell bottomRight = null;

        while (topLeft != null) {
            neighbors.add(topLeft);

            Vector<Point> directions = Helpers.getDirectionsVector();
            boolean isValid = false;

            while (!neighbors.isEmpty()) {
                bottomRight = neighbors.remove();

                isValid = Helpers.checkSlice(in, topLeft, bottomRight);

                if (isValid) {
                    Helpers.markSlice(in, topLeft, bottomRight);
                    break;
                }

                for (short i = 0; i < 5; i++) {
                    int x = bottomRight.x + directions.elementAt(i).x;
                    int y = bottomRight.y + directions.elementAt(i).y;

                    if (x < 0 || y < 0 || x > in.rows - 1 || y > in.columns - 1) {
                        continue;
                    }

                    if (!(in.matrix[x][y].isTaken)) {
                        neighbors.add(in.matrix[x][y]);
                    }
                }
            }

            if (isValid) {
                boolean hasChangesBottom = true;

                while (hasChangesBottom) {
                    hasChangesBottom = false;

                    int numberOfTomatoes = Helpers.getNumberOfTomatoes(in, topLeft, bottomRight);
                    int numberOfMushrooms = Helpers.getNumberOfMushrooms(in, topLeft, bottomRight);

                    for (short i = 0; i < 5; i++) {
                        int x = bottomRight.x + directions.elementAt(i).x;
                        int y = bottomRight.y + directions.elementAt(i).y;

                        if (x < 0 || y < 0) {
                            continue;
                        }

                        Cell testCell = in.matrix[x][y];
                        boolean isCorrect = Helpers.checkSlice(in, topLeft, testCell);

                        if (isCorrect) {
                            if (numberOfMushrooms > numberOfTomatoes) {
                                int numberOfTomatoesNew = Helpers.getNumberOfTomatoes(in, topLeft, testCell);

                                if (numberOfTomatoesNew == numberOfTomatoes) {
                                    bottomRight = testCell;
                                    hasChangesBottom = true;
                                    Helpers.markSlice(in, topLeft, bottomRight);
                                }
                            } else if (numberOfMushrooms == numberOfTomatoes) {
                                bottomRight = testCell;
                                hasChangesBottom = true;
                                Helpers.markSlice(in, topLeft, bottomRight);
                            } else {
                                int numberOfMushroomsNew = Helpers.getNumberOfMushrooms(in, topLeft, testCell);

                                if (numberOfMushroomsNew == numberOfMushrooms) {
                                    bottomRight = testCell;
                                    hasChangesBottom = true;
                                    Helpers.markSlice(in, topLeft, bottomRight);
                                }
                            }
                        }
                    }
                }

                out.add(new OutputData(topLeft, bottomRight));
            }

            topLeft = Helpers.getFirstNotTakenCell(in);
        }

        HashIO.write(out, "out.txt");
    }
}
