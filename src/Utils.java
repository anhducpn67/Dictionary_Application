import java.io.*;
import java.util.Scanner;

public class Utils {

    public static Scanner readFile(String filePath) {
        try {
            File file = new File(filePath);
            return new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println("Error: Can't read " + filePath);
            e.printStackTrace();
        }
        return null;
    }

    public static PrintWriter writeFile(String filePath) {
        try {
            File file = new File(filePath);
            return new PrintWriter(file);
        } catch (IOException e) {
            System.out.println("Error: Can't write " + filePath);
            e.printStackTrace();
        }
        return null;
    }
}
