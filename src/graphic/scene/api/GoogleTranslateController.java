package graphic.scene.api;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import graphic.dialog.ConfirmDialog;
import graphic.scene.primary.PrimaryStageController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import module.APIGoogleTranslate;
import module.TextToSpeech;
import utility.ProjectConfig;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GoogleTranslateController extends PrimaryStageController {
    @FXML
    private JFXTextArea englishText;

    @FXML
    private JFXTextArea translatedText;

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

    public void translate() {
        String text = englishText.getText();
        String translated = APIGoogleTranslate.translate(text);
        translatedText.setText(translated);
    }

    public void englishSpeak() {
        String text = englishText.getText();
        TextToSpeech.speak(text);
    }

    public void translatedSpeak() {
        String text = translatedText.getText();
        TextToSpeech.speak(text);
    }

    public static Scene getScene() throws IOException {
        Parent root = FXMLLoader.load(GoogleTranslateController.class.getResource("GoogleTranslateScene.fxml"));
        return new Scene(root);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
