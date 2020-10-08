package graphic.scene;

import module.TextToSpeech;
import com.jfoenix.controls.JFXTextArea;
import javafx.fxml.FXML;
import module.APIGoogleTranslate;

public class GoogleTranslateScene {
    @FXML
    private JFXTextArea englishText;

    @FXML
    private JFXTextArea translatedText;

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
}
