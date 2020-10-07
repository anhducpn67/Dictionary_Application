package graphic.primaryStage;

import audio.TextToSpeech;
import com.jfoenix.controls.JFXColorPicker;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import dictionary.DictionaryManagement;
import graphic.scene.AddWordScene;
import graphic.scene.EditWordScene;
import graphic.dialog.ConfirmDialog;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.web.WebView;

import java.io.IOException;
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
    private WebView wordExplainView;

    @FXML
    private FontAwesomeIconView speakerIcon;

    @FXML
    private VBox leftVBox;

    @FXML
    private ListView listView;

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
        wordExplainView.getEngine().loadContent(htmlOfSearchWord);
        speakerIcon.setVisible(true);
    }

    public void deleteWord() throws SQLException {
        String word = searchTextField.getText();
        ConfirmDialog deleteConfirm = new ConfirmDialog();
        boolean isConfirm = deleteConfirm.show("Delete", "Are you sure want to delete this word?");
        if (isConfirm) {
            wordExplainView.getEngine().loadContent("<h1>Chúng tôi không tìm thấy từ mà bạn yêu cầu.</h1>");
            myDictionary.deleteWord(word);
            printRelativeWords();
        }
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
        BorderPane.setMargin(borderPane.getCenter(), new Insets(0, 5, 5, 5));
    }

    public void setWordExplainScene() {
        borderPane.setCenter(wordExplainScene);
    }

    public void setEditWordScene() {
        EditWordScene editWordScene = EditWordScene.getEditWordScene();
        editWordScene.editWord(searchTextField.getText());
        borderPane.setCenter(editWordScene.scene);
        BorderPane.setMargin(borderPane.getCenter(), new Insets(0, 5, 5, 5));
    }

    public void setGoogleTranslateScene() {
        Parent GoogleTranslateScene = null;
        try {
            GoogleTranslateScene = FXMLLoader.load(getClass().getResource("../scene/GoogleTranslate.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        borderPane.setCenter(GoogleTranslateScene);
        BorderPane.setMargin(borderPane.getCenter(), new Insets(0, 5, 5, 5));
    }

}
