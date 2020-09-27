import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;

public class CenterLayout extends UserInterface {
    public static void createCenterLayout() {
        webView.setFontScale(1.25);
        VBox vBox = new VBox(speakButton, webView);
        vBox.setStyle("-fx-background-color: #ffffff;");
        searchButton.setOnAction(click -> {
            String searchWord = searchTextField.getText();
            String htmlOfSearchWord = myDictionary.dictionaryLookup(searchWord);
            webView.getEngine().loadContent(htmlOfSearchWord);
            borderPane.setCenter(vBox);
        });
        searchTextField.setOnAction(enter -> {
            String searchWord = searchTextField.getText();
            String htmlOfSearchWord = myDictionary.dictionaryLookup(searchWord);
            webView.getEngine().loadContent(htmlOfSearchWord);
            borderPane.setCenter(vBox);
        });
        speakButton.setOnAction(click -> {
            String searchWord = searchTextField.getText();
            String audioPath = ProjectConfig.audioPath + searchWord + ".mp3";
            Utils.playAudio(audioPath);
        });
    }
}
