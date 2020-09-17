public class DictionaryCommandline extends DictionaryManagement {
    public void showAllWords() {
        System.out.printf("%-15s %-20s %-15s%n", "No", "English", "Vietnamese");
        for (int i = 0; i < allWords.size(); i++) {
            System.out.printf("%-15d %-20s %-15s%n", (i + 1), allWords.get(i).wordTarget, allWords.get(i).wordExplain);
        }
    }
    public void dictionaryBasic() {
        insertFromCommandline();
        showAllWords();
    }

    public static void main(String[] args) {
        DictionaryCommandline myDictionary = new DictionaryCommandline();
        myDictionary.dictionaryBasic();
    }
}
