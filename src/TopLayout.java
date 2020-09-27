import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class TopLayout extends UserInterface {
    public static void createTopLayout() {
        StackPane stackPane = new StackPane();
        stackPane.getChildren().add(titleText);
        stackPane.setStyle("-fx-background-color: #338a99;");
        stackPane.setPadding(new Insets(10, 10, 10, 10));

        HBox hBox = new HBox();
        hBox.getChildren().addAll(searchTextField, searchButton);
        hBox.setStyle("-fx-background-color: #338a99;");

        VBox vBox = new VBox();
        vBox.getChildren().addAll(stackPane, hBox);
        vBox.setStyle("-fx-background-color: #338a99;");

        borderPane.setTop(vBox);
    }
}
