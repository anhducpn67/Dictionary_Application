package graphic.scene.favorite;

import com.jfoenix.controls.JFXButton;
import graphic.dialog.ConfirmDialog;
import graphic.scene.primary.PrimaryStageController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import utility.ProjectConfig;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class FavoriteController extends PrimaryStageController implements Initializable {

    @FXML
    ListView listFavoriteWords;

    @FXML
    public JFXButton backButton;

    public void setBackButton() throws IOException {
        ConfirmDialog cancelConfirm = new ConfirmDialog();
        boolean isConfirm = cancelConfirm.show("Add New Word",
                "Are you sure want to back?");
        if (isConfirm) {
            ProjectConfig.primaryStage.setScene(PrimaryStageController.getScene());
        }
    }

    public void getFavoriteWords() {
        listFavoriteWords.getItems().clear();
        ResultSet resultSet = myDictionary.getFavoriteWord();
        while (true) {
            try {
                if (!resultSet.next()) break;
                listFavoriteWords.getItems().add(resultSet.getString("word"));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static Scene getScene() throws IOException {
        Parent root = FXMLLoader.load(FavoriteController.class.getResource("FavoriteScene.fxml"));
        return new Scene(root);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        getFavoriteWords();
    }
}
