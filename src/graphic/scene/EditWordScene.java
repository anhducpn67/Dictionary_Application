package graphic.scene;

import graphic.dialog.ConfirmDialog;
import graphic.dialog.ErrorDialog;
import graphic.dialog.InformationDialog;

public class EditWordScene extends Scene {

    private static EditWordScene editWordScene;
    public static String curWord;

    private EditWordScene() {

    }

    public static EditWordScene getEditWordScene() {
        if (editWordScene == null) {
            editWordScene = new EditWordScene();
            return editWordScene;
        }
        return editWordScene;
    }

    // Initialize edit button
    {
        button.setOnAction(click -> {
            ConfirmDialog editWordConfirm = new ConfirmDialog();
            boolean isConfirm = editWordConfirm.show("Edit Word",
                    "Are you sure want to edit this word?");
            if (isConfirm) {
                myDictionary.editWord(curWord, htmlEditor.getHtmlText());
                InformationDialog savedDialog = new InformationDialog();
                savedDialog.show("Edit Word", "Edited successfully");
            }
        });
    }

    public void editWord(String word) {
       String html = myDictionary.dictionaryLookup(word);
       curWord = word;
       htmlEditor.setHtmlText(html);
    }
}
