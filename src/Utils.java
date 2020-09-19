import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Utils {

    public static Scanner readFile(String filePath) {
        File file = new File(filePath);
        try {
            return new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println("Error: Can't open " + filePath);
        }
        return null;
    }
}
