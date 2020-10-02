package graphic.additionalScene;

import dictionary.Word;
import graphic.dialog.ErrorDialog;
import graphic.dialog.InformationDialog;
import utility.Utils;

public class AddWordScene extends AdditionalScene {
    private static AddWordScene addWordScene;

    private AddWordScene() {

    }

    public static AddWordScene getAddWordScene() {
        if (addWordScene == null) {
            addWordScene = new AddWordScene();
            return addWordScene;
        }
        return addWordScene;
    }

    {
        button.setText("Add");
        button.setOnAction(click -> {
            boolean isSave = addWord();
            if (isSave) {
                InformationDialog addDialog = new InformationDialog();
                addDialog.show("Add New Word", "Added successfully");
            } else {
                ErrorDialog addDialog = new ErrorDialog();
                addDialog.show("Add New Word", "Error: The word already had");
            }
        });
    }

    public boolean addWord() {
        String html = this.htmlEditor.getHtmlText();
        String word = Utils.getWordFromHtlmText(html);
        Word newWord = new Word(word, html);
        return myDictionary.saveWord(newWord);
    }
}
