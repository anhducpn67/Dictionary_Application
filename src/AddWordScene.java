import com.jfoenix.controls.JFXButton;
import javafx.geometry.Insets;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.web.HTMLEditor;

public class AddWordScene {

    public static DictionaryManagement myDictionary = DictionaryManagement.getDictionaryManagement();

    public static HTMLEditor htmlEditor = new HTMLEditor();
    public static JFXButton saveButton = new JFXButton("Save");
    public static VBox addWordScene = new VBox();

    public static void initialize() {
        initializeSaveButton();
        initializeHtmlEditor();
        addWordScene.getChildren().addAll(htmlEditor, saveButton);
        addWordScene.setPadding(new Insets(0, 5, 5, 0));
    }

    public static void initializeHtmlEditor() {
        htmlEditor.setPrefHeight(750);
        VBox.setVgrow(htmlEditor, Priority.ALWAYS);
    }

    public static void initializeSaveButton() {
        saveButton.setFont(new Font("system", 18));
        saveButton.setPrefHeight(50);
        saveButton.setPrefWidth(100);
        saveButton.setStyle("-fx-background-color: #388E3C");
        saveButton.setOnAction(click -> actionAddWord());
    }

//    TODO: make a dialog "Saved"
    public static void actionAddWord() {
        String html = htmlEditor.getHtmlText();
        String word = Utils.getWordFromHtlmText(html);
        Word newWord = new Word(word, html);
        myDictionary.saveNewWord(newWord);
    }
}
