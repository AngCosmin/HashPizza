import java.io.*;
import java.util.Scanner;

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
}
