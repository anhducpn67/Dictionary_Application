import dictionary.DictionaryManagement;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import utility.ProjectConfig;
import utility.Utils;

import java.io.IOException;

public class Main extends Application {

    public static void primaryStageConfig(Stage primaryStage) {
        primaryStage.setTitle("dictionary.Dictionary Application");
        Image dictionary_icon = Utils.loadImage(ProjectConfig.dictionaryIconPath);
        primaryStage.getIcons().add(dictionary_icon);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStageConfig(primaryStage);
        Parent root = FXMLLoader.load(getClass().getResource("graphic/primaryStage/primaryStage.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        DictionaryManagement myDictionary = DictionaryManagement.getDictionaryManagement();
        myDictionary.loadWordFromDatabase();
        launch(args);
    }
}
