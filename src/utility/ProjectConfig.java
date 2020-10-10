package utility;

import javafx.scene.image.Image;
import javafx.stage.Stage;

public class ProjectConfig {
    final public static String databasePath = "jdbc:sqlite:D:/Study/OOP/Dictionary_Application/resource/dictionary/dict_hh.db";
    final public static String databaseName = "av";

    final public static String dictionaryIconPath = "./resource/images/dictionary_icon.png";
    public static Stage primaryStage = new Stage();
    static {
        primaryStage.setTitle("Dictionary");
        Image dictionary_icon = Utils.loadImage(ProjectConfig.dictionaryIconPath);
        primaryStage.getIcons().add(dictionary_icon);
    }
    final public static int numberDidYouMeanWord = 3;
}
