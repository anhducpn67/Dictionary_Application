import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXColorPicker;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.web.WebView;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Controller {

    DictionaryManagement myDictionary = DictionaryManagement.getDictionaryManagement();

    @FXML
    private BorderPane borderPane;

    @FXML
    private TextField searchTextField;

    @FXML
    private JFXColorPicker colorPicker;

    @FXML
    private Text wordText;

    @FXML
    private WebView webViewBottom;

    @FXML
    private FontAwesomeIconView speakerIcon;

    @FXML
    private VBox leftVBox;

    @FXML
    private ListView listView;

    @FXML
    private JFXButton adderButton;

    @FXML
    public void changeColor() {
        Color selectedColor = colorPicker.getValue();
        borderPane.setBackground(new Background(new BackgroundFill(
                selectedColor, CornerRadii.EMPTY, Insets.EMPTY
        )));
        leftVBox.setBackground(new Background(new BackgroundFill(
                selectedColor, CornerRadii.EMPTY, Insets.EMPTY
        )));
        listView.setBackground(new Background(new BackgroundFill(
                selectedColor, CornerRadii.EMPTY, Insets.EMPTY
        )));
    }

    public void searchWord() {
        String searchWord = searchTextField.getText();
        String htmlOfSearchWord = myDictionary.dictionaryLookup(searchWord);
        searchWord = "<h1>" + searchWord + "</h1>";
        htmlOfSearchWord = htmlOfSearchWord.substring(searchWord.length());
        searchWord = searchTextField.getText();
        wordText.setText(searchWord);
        htmlOfSearchWord = "<body style=" + "\"background-color:#FFFFFFFF;" + "\">" + htmlOfSearchWord;
        htmlOfSearchWord = htmlOfSearchWord + "</body>";
        webViewBottom.getEngine().loadContent(htmlOfSearchWord);
        speakerIcon.setVisible(true);
    }

    public void printRelativeWords() throws SQLException {
        listView.getItems().clear();
        ResultSet relativeWord = myDictionary.dictionarySearch(searchTextField.getText());
        while (relativeWord.next()) {
            listView.getItems().add(relativeWord.getString("word"));
        }
    }

    public void selectWordInListView() {
        ObservableList selectedIndices = listView.getSelectionModel().getSelectedItems();
        searchTextField.setText((String) selectedIndices.get(0));
        searchWord();
    }

    public void pronounceWord() {
        String searchWord = searchTextField.getText();
        String audioPath = ProjectConfig.audioPath + searchWord + ".mp3";
        Utils.playAudio(audioPath);
    }

    public void setAddWordScene() {
        borderPane.setCenter(AddWordScene.vBox);
    }
}
