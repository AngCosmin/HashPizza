import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class HashIO {
    public static InputData read (String filename) {
        InputData data = new InputData();

        try {
            FileReader file = new FileReader(filename);
            Scanner sc = new Scanner(file);

            // First line from file
            data.rows = sc.nextShort();
            data.columns = sc.nextShort();
            data.minNumberOfEachIncredient = sc.nextShort();
            data.maxNumberOfCells = sc.nextShort();

            // Matrix
            data.matrix = new Cell[data.rows][data.columns];

            for (short i = 0; i < data.rows; i++) {
                String line = sc.nextLine();

                for (short j = 0; j < line.length(); j++) {
                    if (line.charAt(j) == 'T') {
                        data.matrix[i][j] = new Tomato(i, j);
                        System.out.println("New tomato");
                    }
                    else {
                        data.matrix[i][j] = new Mushroom(i, j);
                        System.out.println("New mushroom");
                    }
                }
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        return data;
    }

    public static void write (ArrayList<OutputData> data, String filename) {
        try {
            FileWriter file = new FileWriter(filename);
            PrintWriter writer = new PrintWriter(file);

            // Print the number of results
            writer.print(data.size());

            for (int i = 0; i < data.size(); i++) {
                int x0 = data.get(i).topLeft.position.x;
                int y0 = data.get(i).topLeft.position.y;
                int x1 = data.get(i).bottomRight.position.x;
                int y1 = data.get(i).bottomRight.position.y;

                writer.printf("%d %d %d %d\n", x0, y0, x1, y1);
            }

            writer.close();
        }
        catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
}
