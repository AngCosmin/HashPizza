import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class HashIO {
    public static void write (ArrayList<OutputData> data, String filename) {
        try {
            FileWriter file = new FileWriter(filename);
            PrintWriter writer = new PrintWriter(file);

            // Print the number of results
            writer.print(data.size());

            for (int i = 0; i < data.size(); i++) {
                int x0 = data.get(i).topLeft.x;
                int y0 = data.get(i).topLeft.y;
                int x1 = data.get(i).bottomRight.x;
                int y1 = data.get(i).bottomRight.y;

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
