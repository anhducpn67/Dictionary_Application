package graphic.scene;

import dictionary.Word;
import graphic.dialog.ConfirmDialog;
import graphic.dialog.ErrorDialog;
import graphic.dialog.InformationDialog;
import utility.Utils;

public class AddWordScene extends Scene {
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

    // Initialize add button
    {
        button.setOnAction(click -> {
            ConfirmDialog addWordConfirm = new ConfirmDialog();
            boolean isConfirm = addWordConfirm.show("Add New Word",
                    "Are you sure want to add this word?");
            if (isConfirm) {
                boolean isSave = addWord();
                if (isSave) {
                    InformationDialog addDialog = new InformationDialog();
                    addDialog.show("Add New Word", "Added successfully");
                } else {
                    ErrorDialog addDialog = new ErrorDialog();
                    addDialog.show("Add New Word", "Error: The word already had");
                }
            }
        });
    }

    public boolean addWord() {
        String html = this.htmlEditor.getHtmlText();
        String word = Utils.getWordFromHtlmText(html);
        System.out.println(word);
        Word newWord = new Word(word, html);
        return myDictionary.saveWord(newWord);
    }
}
