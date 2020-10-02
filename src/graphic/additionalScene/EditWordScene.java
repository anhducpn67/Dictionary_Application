package graphic.additionalScene;

import graphic.dialog.InformationDialog;

public class EditWordScene extends AdditionalScene {

    private static EditWordScene editWordScene;

    private EditWordScene() {

    }

    public static EditWordScene getEditWordScene() {
        if (editWordScene == null) {
            editWordScene = new EditWordScene();
            return editWordScene;
        }
        return editWordScene;
    }

    public static String curWord;
    {
        button.setText("Edit");
        button.setOnAction(click -> {
            myDictionary.editWord(curWord, htmlEditor.getHtmlText());
            InformationDialog savedDialog = new InformationDialog();
            savedDialog.show("Edit Word", "Edited successfully");
        });
    }

    public void editWord(String word) {
       String html = myDictionary.dictionaryLookup(word);
       curWord = word;
       htmlEditor.setHtmlText(html);
    }
}
