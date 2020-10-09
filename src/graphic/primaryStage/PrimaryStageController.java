package graphic.primaryStage;

import module.LevenshteinDistance;
import module.TextToSpeech;
import com.jfoenix.controls.JFXColorPicker;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import dictionary.DictionaryManagement;
import graphic.scene.AddWordScene;
import graphic.scene.EditWordScene;
import graphic.dialog.ConfirmDialog;
import graphic.scene.FavoriteScene;
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
    private ListView listView;

    @FXML
    private VBox wordExplainScene;

    @FXML
    private ToolBar toolBar;

    @FXML
    private FontAwesomeIconView favorite;

    private static String currentWord;

    @FXML
    public void changeColor() {
        Color selectedColor = colorPicker.getValue();
        borderPane.setBackground(new Background(new BackgroundFill(
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
        currentWord = searchTextField.getText();
        if (myDictionary.isContain(currentWord)) {
            setWordExplainScene();
        } else {
            setDidYouMeanScene();
        }
    }

    public void pronounceWord() {
        TextToSpeech.speak(currentWord);
    }

    public void changeFavoriteStatus() {
        if (favorite.getGlyphName().equals("STAR")) {
            favorite.setGlyphName("STAR_ALT");
            myDictionary.setFavoriteStatus(currentWord, 0);
        } else {
            favorite.setGlyphName("STAR");
            myDictionary.setFavoriteStatus(currentWord, 1);
        }
    }

    public void deleteWord() throws SQLException {
        ConfirmDialog deleteConfirm = new ConfirmDialog();
        boolean isConfirm = deleteConfirm.show("Delete", "Are you sure want to delete this word?");
        if (isConfirm) {
            wordExplainView.getEngine().loadContent("<h1>Chúng tôi không tìm thấy từ mà bạn yêu cầu.</h1>");
            myDictionary.deleteWord(currentWord);
            printSuggestedWords();
        }
    }

    public void printSuggestedWords() throws SQLException {
        listView.getItems().clear();
        ResultSet resultSet = myDictionary.dictionarySearch(searchTextField.getText());
        while (resultSet.next()) {
            listView.getItems().add(resultSet.getString("word"));
        }
    }

    public void getSelectedWordInSuggestedList() {
        ObservableList selectedIndices = listView.getSelectionModel().getSelectedItems();
        searchTextField.setText((String) selectedIndices.get(0));
        searchWord();
    }

    public void setAddWordScene() {
        AddWordScene addWordScene = AddWordScene.getAddWordScene();
        borderPane.setCenter(addWordScene.scene);
        BorderPane.setMargin(borderPane.getCenter(), new Insets(0, 5, 5, 5));
    }

    private void setDidYouMeanScene() {
        String htmlOfSearchWord = "<h1>Chúng tôi không tìm thấy từ mà bạn yêu cầu.</h1>";
        htmlOfSearchWord = htmlOfSearchWord + "<h1>Có phải từ bạn muốn tìm kiếm là: </h1>";
        String[] result = LevenshteinDistance.getTop1Score(currentWord);
        for (String word: result) {
            if (word.equals("null")) {
                continue;
            }
            htmlOfSearchWord = htmlOfSearchWord + "<h1>" + word + "</h1>";
        }
        htmlOfSearchWord = "<body style=" + "\"background-color:#FFFFFFFF;" + "\">" + htmlOfSearchWord + "</body>";
        wordExplainView.getEngine().loadContent(htmlOfSearchWord);
        borderPane.setCenter(wordExplainScene);
    }

    public void setWordExplainScene() {
        String htmlOfSearchWord = myDictionary.dictionaryLookup(currentWord);
        htmlOfSearchWord = "<body style=" + "\"background-color:#FFFFFFFF;" + "\">" + htmlOfSearchWord + "</body>";
        int isFavorite = myDictionary.isFavorite(currentWord);
        wordExplainView.getEngine().loadContent(htmlOfSearchWord);
        if (isFavorite == 0) {
            favorite.setGlyphName("STAR_ALT");
        } else {
            favorite.setGlyphName("STAR");
        }
        speakerIcon.setVisible(true);
        favorite.setVisible(true);
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

    public void setFavoriteWordsScene() {
        FavoriteScene favoriteScene = FavoriteScene.getFavoriteScene();
        borderPane.setCenter(favoriteScene.getScene());
        BorderPane.setMargin(borderPane.getCenter(), new Insets(0, 5, 5, 5));
    }
}