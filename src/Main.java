import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    public static DictionaryCommandline myDictionary = new DictionaryCommandline();

    public static void primaryStageConfig(Stage primaryStage) {
        primaryStage.setTitle("Dictionary Application");
        primaryStage.setHeight(ProjectConfig.primaryStageHeight);
        primaryStage.setWidth(ProjectConfig.primaryStageWidth);
        Image dictionary_icon = Utils.readImage(ProjectConfig.dictionaryIconPath);
        primaryStage.getIcons().add(dictionary_icon);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStageConfig(primaryStage);
        primaryStage.show();
    }

    public static void main(String[] args) {
        myDictionary.insertFromFile();
        launch(args);
    }
}
