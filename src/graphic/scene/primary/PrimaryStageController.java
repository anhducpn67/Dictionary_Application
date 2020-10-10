package graphic.scene.primary;

import graphic.scene.api.GoogleTranslateController;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import module.LevenshteinDistance;
import module.TextToSpeech;
import com.jfoenix.controls.JFXColorPicker;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import dictionary.DictionaryManagement;
import graphic.dialog.ConfirmDialog;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.web.WebView;
import graphic.scene.add.AddWordController;
import graphic.scene.favorite.FavoriteController;
import graphic.scene.edit.EditWordController;
import utility.ProjectConfig;
import utility.Utils;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class PrimaryStageController implements Initializable  {

    protected DictionaryManagement myDictionary = DictionaryManagement.getDictionaryManagement();

    @FXML
    public BorderPane borderPane;

    @FXML
    public TextField searchTextField;

    @FXML
    public JFXColorPicker colorPicker;

    @FXML
    public WebView wordExplainView;

    @FXML
    public FontAwesomeIconView speakerIcon;

    @FXML
    public ListView listView;

    @FXML
    public VBox wordExplainScene;

    @FXML
    public ToolBar toolBar;

    @FXML
    public FontAwesomeIconView favorite;

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

    public static String currentWord;

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

    public void setAddWordScene() throws IOException {
        ProjectConfig.primaryStage.setScene(AddWordController.getScene());
    }

    private void setDidYouMeanScene() {
        String html = "<h1>Chúng tôi không tìm thấy từ mà bạn yêu cầu.</h1>";
        html = html + "<h1>Có phải từ bạn muốn tìm kiếm là: </h1>";
        String[] result = LevenshteinDistance.getTopScore(currentWord);
        for (String word: result) {
            if (word == null){
                continue;
            }
            html = html + "<h1>" + word + "</h1>";
        }
        html = "<body style=" + "\"background-color:#FFFFFFFF;" + "\">" + html + "</body>";
        wordExplainView.getEngine().loadContent(html);
        borderPane.setCenter(wordExplainScene);
    }

    public void setWordExplainScene() {
        String htmlOfSearchWord = myDictionary.dictionaryLookup(currentWord);
        htmlOfSearchWord = "<body style=" + "\"background-color:#FFFFFFFF;" + "\">" + htmlOfSearchWord + "</body>";
        int isFavorite = myDictionary.isFavorite(currentWord);
        htmlOfSearchWord = Utils.setNotEditable(htmlOfSearchWord);
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

    public void setEditWordScene() throws IOException {
        ProjectConfig.primaryStage.setScene(EditWordController.getScene());
    }

    public void setGoogleTranslateScene() throws IOException {
        ProjectConfig.primaryStage.setScene(GoogleTranslateController.getScene());
    }

    public void setFavoriteWordsScene() throws IOException {
        ProjectConfig.primaryStage.setScene(FavoriteController.getScene());
    }

    public static Scene getScene() throws IOException {
        Parent root = FXMLLoader.load(PrimaryStageController.class.getResource("primaryStage.fxml"));
        return new Scene(root);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            printSuggestedWords();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String html = "<h1> Ứng dụng từ điển.</h1>";
        html = html + "<h1> Tác giả: </h1>";
        html = html + "<h1> Nguyễn Trần Anh Đức - 19020007 </h1>";
        html = html + "<h1> Nguyễn Kim Đức - 19020006 </h1>";
        wordExplainView.getEngine().loadContent(html);
    }
}