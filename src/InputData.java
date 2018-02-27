import java.io.*;
import java.util.Scanner;
import java.awt.*;
import java.util.Vector;

public class InputData {
    // Input elements

    // short: -32,768 to 32,767
    public short rows;
    public short columns;
    public short minNumberOfEachIncredient;
    public short maxNumberOfCells;

    // Matrix of chars
    public Cell[][] matrix;

    public void read (String filename) {
        try {
            FileReader file = new FileReader(filename);
            Scanner sc = new Scanner(file);

            // First line from file
            this.rows = sc.nextShort();
            this.columns = sc.nextShort();
            this.minNumberOfEachIncredient = sc.nextShort();
            this.maxNumberOfCells = sc.nextShort();
            sc.nextLine();

            // Matrix
            this.matrix = new Cell[this.rows][this.columns];

            for (short i = 0; i < this.rows; i++) {
                String line = sc.nextLine();

                for (short j = 0; j < this.columns; j++) {
                    if (line.charAt(j) == 'T') {
                        matrix[i][j] = new Tomato(i, j);
                    }
                    else {
                        matrix[i][j] = new Mushroom(i, j);
                    }
                }
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    public void markSlice(Cell topLeft, Cell bottomRight) {
        for (int i = topLeft.x; i <= bottomRight.x; i++) {
            for (int j = topLeft.y; j <= bottomRight.y; j++) {
                this.matrix[i][j].isTaken = true;
            }
        }
    }

    public Cell getFirstNotTakenCell () {
        for (short i = 0; i < this.rows; i++) {
            for (short j = 0; j < this.columns; j++) {
                if (!this.matrix[i][j].isTaken) {
                    Vector<Point> directions = Helpers.getDirectionsVector();

                    for (int k = 0; k < 5; k++) {
                        int x = i + directions.elementAt(k).x;
                        int y = j + directions.elementAt(k).y;

                        if (x < 0 || y < 0 || x > this.rows - 1 || y > this.columns - 1) {
                            continue;
                        }

                        if (!this.matrix[x][y].isTaken) {
                            return this.matrix[i][j];
                        }
                    }
                    this.matrix[i][j].isTaken = true;
                }
            }
        }

        return null;
    }
}
