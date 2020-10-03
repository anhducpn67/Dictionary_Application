package graphic.primaryStage;

import audio.TextToSpeech;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXColorPicker;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import dictionary.DictionaryManagement;
import graphic.additionalScene.AddWordScene;
import graphic.additionalScene.EditWordScene;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.web.WebView;
import utility.ProjectConfig;
import utility.Utils;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PrimaryStageController {

    DictionaryManagement myDictionary = DictionaryManagement.getDictionaryManagement();

    @FXML
    private BorderPane borderPane;

    @FXML
    private TextField searchTextField;

    @FXML
    private JFXColorPicker colorPicker;

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
    private JFXButton editButton;

    @FXML
    private VBox wordExplainScene;

    @FXML
    private ToolBar toolBar;

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
        toolBar.setBackground(new Background(new BackgroundFill(
                selectedColor, CornerRadii.EMPTY, Insets.EMPTY
        )));
    }

    public void searchWord() {
        setWordExplainScene();
        String searchWord = searchTextField.getText();
        String htmlOfSearchWord = myDictionary.dictionaryLookup(searchWord);
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
        String word = searchTextField.getText();
        TextToSpeech.speak(word);
    }

    public void setAddWordScene() {
        AddWordScene addWordScene = AddWordScene.getAddWordScene();
        borderPane.setCenter(addWordScene.scene);
    }

    public void setWordExplainScene() {
        borderPane.setCenter(wordExplainScene);
    }

    public void setEditWordScene() {
        EditWordScene editWordScene = EditWordScene.getEditWordScene();
        editWordScene.editWord(searchTextField.getText());
        borderPane.setCenter(editWordScene.scene);
    }
}
