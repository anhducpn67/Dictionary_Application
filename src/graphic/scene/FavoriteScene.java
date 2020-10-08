package graphic.scene;

import com.jfoenix.controls.JFXListView;
import dictionary.DictionaryManagement;
import javafx.scene.layout.Pane;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FavoriteScene {

    private static FavoriteScene favoriteScene;
    private Pane pane = new Pane();
    {
        pane.setStyle("-fx-background-color: GREY");
        pane.setPrefWidth(500);
        pane.setPrefHeight(600);
    }
    private JFXListView listFavoriteWords = new JFXListView();
    {
        pane.getChildren().addAll(listFavoriteWords);
        listFavoriteWords.setPrefWidth(500);
        listFavoriteWords.setPrefHeight(600);
    }
    private DictionaryManagement myDictionary = DictionaryManagement.getDictionaryManagement();

    private FavoriteScene() {

    }

    public static FavoriteScene getFavoriteScene() {
        if (favoriteScene == null) {
            favoriteScene = new FavoriteScene();
            return favoriteScene;
        }
        return favoriteScene;
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

    public Pane getScene() {
        getFavoriteWords();
        return pane;
    }

}
