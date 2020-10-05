package graphic.scene;

import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import dictionary.DictionaryManagement;
import javafx.geometry.Insets;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.web.HTMLEditor;

public class Scene {
    public static DictionaryManagement myDictionary = DictionaryManagement.getDictionaryManagement();
    public HTMLEditor htmlEditor = new HTMLEditor();
    {
        htmlEditor.setOpacity(0.85);
        VBox.setVgrow(htmlEditor, Priority.ALWAYS);
    }
    public JFXButton button = new JFXButton();
    {
        FontAwesomeIconView saveIcon = new FontAwesomeIconView();
        saveIcon.setGlyphName("SAVE");  saveIcon.setSize("1.8em");
        button.setText("Save");
        button.setGraphic(saveIcon);
        button.setButtonType(JFXButton.ButtonType.RAISED);
        button.setFont(new Font("system", 18));
        button.setPrefHeight(30);
        button.setPrefWidth(100);
        button.setStyle("-fx-background-color: GREEN; -fx-text-fill: WHITE");
        VBox.setMargin(button, new Insets(5, 5, 5, 5));
    }
    public VBox scene = new VBox();
    {
        scene.getChildren().addAll(htmlEditor, button);
        scene.setStyle("-fx-background-color: GREY;");
    }
}
