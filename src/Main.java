import java.awt.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        InputData in = new InputData();
        in.read("big.in");
        ArrayList<OutputData> out = new ArrayList<>();

        Queue<Cell> neighbors = new LinkedList<>();
        Cell topLeft = in.matrix[0][0];
        Cell bottomRight = null;
        boolean isValid = false;

        while (topLeft != null) {
            neighbors.add(topLeft);
            Set<Cell> visited = new HashSet<>();
            Vector<Point> directions = Helpers.getDirectionsVector();
            visited.add(topLeft);

            while (!neighbors.isEmpty()) {
                bottomRight = neighbors.remove();


                isValid = Helpers.checkSlice(in, topLeft, bottomRight);
                System.out.println("Queue " + topLeft.x + " " + topLeft.y + " " + bottomRight.x + " " + bottomRight.y + " " + isValid);

                if (isValid) {
                    break;
                }

                for (short i = 0; i < 5; i++) {
                    int x = bottomRight.x + directions.elementAt(i).x;
                    int y = bottomRight.y + directions.elementAt(i).y;

                    if (x < 0 || y < 0 || x > in.rows - 1 || y > in.columns - 1) {
                        continue;
                    }

                    if (!(in.matrix[x][y].isTaken) && !(visited.contains(in.matrix[x][y]))) {
                        neighbors.add(in.matrix[x][y]);
                        visited.add(in.matrix[x][y]);
                    }
                }
            }

            if (isValid) {
                boolean hasChangesBottom = true;

                
                while (hasChangesBottom == true) {
                    hasChangesBottom = false;
                    
                    int numberOfTomatoes = Helpers.getNumberOfTomatoes(in, topLeft, bottomRight);
                    int numberOfMushrooms = Helpers.getNumberOfMushrooms(in, topLeft, bottomRight);
                    
                    for (short i = 0; i < 4; i++) {
                        int x = bottomRight.x + directions.elementAt(i).x;
                        int y = bottomRight.y + directions.elementAt(i).y;
                        
                        if (x < 0 || y < 0 || x > in.rows - 1 || y > in.columns - 1) {
                            continue;
                        }
                        
                        Cell testCell = in.matrix[x][y];

                        if (testCell.isTaken) {
                            continue;
                        }

                        boolean isCorrect = Helpers.checkSlice(in, topLeft, testCell);
                        
                        System.out.println("topleft " + topLeft.x + " " + topLeft.y);
                        System.out.println("Corect " + isCorrect + " " + testCell.x + " " + testCell.y);
                        
                        if (isCorrect && Helpers.sliceDimensions(topLeft, bottomRight) <= Helpers.sliceDimensions(topLeft, testCell)) {
                            if (numberOfMushrooms > numberOfTomatoes) {
                                int numberOfTomatoesNew = Helpers.getNumberOfTomatoes(in, topLeft, testCell);

                                if (numberOfTomatoesNew == numberOfTomatoes) {
                                    bottomRight = testCell;
                                    hasChangesBottom = true;
                                }
                            } else if (numberOfMushrooms == numberOfTomatoes) {
                                bottomRight = testCell;
                                hasChangesBottom = true;
                            } else {
                                int numberOfMushroomsNew = Helpers.getNumberOfMushrooms(in, topLeft, testCell);

                                if (numberOfMushroomsNew == numberOfMushrooms) {
                                    bottomRight = testCell;
                                    hasChangesBottom = true;
                                }
                            }
                            break;
                        }
                    }
                }

                out.add(new OutputData(topLeft, bottomRight));
                in.markSlice(topLeft, bottomRight);
            }

            topLeft = Helpers.getFirstNotTakenCell(in);
            neighbors.clear();
            //System.out.println("taken " + in.matrix[0][0].isTaken);
        }
        
        HashIO.write(out, "big.txt");
    }
}
