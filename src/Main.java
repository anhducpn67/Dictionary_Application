import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    public static void primaryStageConfig(Stage primaryStage) {
        primaryStage.setTitle("Dictionary Application");
        Image dictionary_icon = Utils.loadImage(ProjectConfig.dictionaryIconPath);
        primaryStage.getIcons().add(dictionary_icon);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStageConfig(primaryStage);
        AddWordScene.initialize();
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        DictionaryManagement dictionaryManagement = DictionaryManagement.getDictionaryManagement();
        dictionaryManagement.loadWordFromDatabase();
        launch(args);
    }
}
