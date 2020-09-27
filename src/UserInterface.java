import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.web.WebView;

public class UserInterface {
    public static DictionaryManagement myDictionary = DictionaryManagement.getDictionaryManagement();
    public static Button searchButton = null;
    public static Button speakButton = null;
    public static TextField searchTextField = null;
    public static Text titleText = null;
    public static BorderPane borderPane = new BorderPane();
    public static WebView webView = new WebView();

    private static void createTitleText() {
        titleText = new Text();
        titleText.setText("ENGLISH - VIETNAMESE");
        titleText.setFont(Font.font("harrington", FontWeight.BOLD,
                FontPosture.REGULAR, ProjectConfig.titleSize));
        titleText.setFill(ProjectConfig.titleFillColor);
        titleText.setStroke(ProjectConfig.titleStrokeColor);
    }

    private static void createSearchButton() {
        ImageView lookupIcon = new ImageView(Utils.loadImage(ProjectConfig.lookupIconPath));
        lookupIcon.setFitHeight(30);    lookupIcon.setFitWidth(30);
        searchButton = new Button("", lookupIcon);
        HBox.setHgrow(searchButton, Priority.ALWAYS);
    }

    private static void createSearchTextField() {
        searchTextField = new TextField();
        searchTextField.setFont(Font.font(18));
        searchTextField.setPromptText("Search English");
        searchTextField.setAlignment(Pos.BASELINE_LEFT);
        HBox.setHgrow(searchTextField, Priority.ALWAYS);
    }

    public static void createSpeakButton() {
        ImageView speakerIcon = new ImageView(Utils.loadImage(ProjectConfig.speakerIconPath));
        speakerIcon.setFitHeight(25);    speakerIcon.setFitWidth(25);
        speakButton = new Button("US", speakerIcon);
    }

    public static void createUserInterface() {
        createTitleText();
        createSearchButton();
        createSpeakButton();
        createSearchTextField();
        TopLayout.createTopLayout();
        CenterLayout.createCenterLayout();
    }
}
