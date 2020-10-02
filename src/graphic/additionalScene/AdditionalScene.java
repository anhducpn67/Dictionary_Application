package graphic.additionalScene;

import com.jfoenix.controls.JFXButton;
import dictionary.DictionaryManagement;
import javafx.geometry.Insets;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.web.HTMLEditor;

public class AdditionalScene {
    public static DictionaryManagement myDictionary = DictionaryManagement.getDictionaryManagement();
    public HTMLEditor htmlEditor = new HTMLEditor();
    {
        htmlEditor.setPrefHeight(750);
        VBox.setVgrow(htmlEditor, Priority.ALWAYS);
    }
    public JFXButton button = new JFXButton();
    {
        button.setFont(new Font("system", 18));
        button.setPrefHeight(50);
        button.setPrefWidth(100);
        button.setStyle("-fx-background-color: #388E3C");
    }
    public VBox scene = new VBox();
    {
        scene.getChildren().addAll(htmlEditor, button);
        scene.setPadding(new Insets(0, 5, 5, 0));
    }
}
