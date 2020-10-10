package utility;

import javafx.scene.image.Image;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.*;
import java.util.Scanner;

public class Utils {

    public static Scanner readFile(String filePath) {
        Scanner scanner = null;
        try {
            File file = new File(filePath);
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println("Error: Can't read " + filePath);
            e.printStackTrace();
        }
        return scanner;
    }

    public static PrintWriter writeFile(String filePath) {
        PrintWriter printWriter = null;
        try {
            File file = new File(filePath);
            printWriter = new PrintWriter(file);
        } catch (IOException e) {
            System.out.println("Error: Can't write to " + filePath);
            e.printStackTrace();
        }
        return printWriter;
    }

    public static String getWordFromHtlmText(String htmlText) {
        Document document = Jsoup.parse(htmlText, "UTF-8");
        Element element = document.body().select("> p").first();
        return element.text();
    }

    public static Image loadImage(String filePath) {
        Image image = null;
        try {
            FileInputStream input = new FileInputStream(filePath);
            image = new Image(input);
        } catch (FileNotFoundException e) {
            System.out.println("Error: Can't load image " + filePath);
            e.printStackTrace();
        }
        return image;
    }

    public static String setNotEditable(String html) {
        if(html.contains("contenteditable=\"true\"")){
            html = html.replace("contenteditable=\"true\"", "contenteditable=\"false\"");
        }
        return html;
    }
}
