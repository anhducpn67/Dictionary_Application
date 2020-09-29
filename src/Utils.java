import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

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
        StringBuilder word = new StringBuilder();
        int index = htmlText.indexOf("</font>") - 1;
        while (htmlText.charAt(index) != '>') {
            word.insert(0, htmlText.charAt(index));
            index--;
        }
        return word.toString();
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

    public static void playAudio(String audioPath) {
        File file = new File(audioPath);
        if (file.exists()) {
            Media media = new Media(new File(audioPath).toURI().toString());
            MediaPlayer mediaPlayer = new MediaPlayer(media);
            mediaPlayer.play();
        }
    }
}
