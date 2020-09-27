import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class GraphicJavaFX extends Application {

    public static void primaryStageConfig(Stage primaryStage) {
        primaryStage.setTitle("Dictionary Application");
        primaryStage.setHeight(ProjectConfig.primaryStageHeight);
        primaryStage.setWidth(ProjectConfig.primaryStageWidth);
        Image dictionary_icon = Utils.loadImage(ProjectConfig.dictionaryIconPath);
        primaryStage.getIcons().add(dictionary_icon);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStageConfig(primaryStage);
        UserInterface.createUserInterface();

        Scene scene = new Scene(UserInterface.borderPane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        DictionaryManagement dictionaryManagement = DictionaryManagement.getDictionaryManagement();
        dictionaryManagement.loadWordFromDatabase();
        launch(args);
    }
}
