import java.util.Scanner;

public class DictionaryCommandline extends DictionaryManagement {

    public void showAllWords() {
        System.out.printf("%-15s %-20s %-15s%n", "No", "English", "Vietnamese");
        for (int i = 0; i < allWords.size(); i++) {
            System.out.printf("%-15d %-20s %-15s%n", (i + 1), allWords.get(i).wordTarget, allWords.get(i).wordExplain);
        }
    }

    public void dictionarySearcher() {
        System.out.println("Write the word you want to search: ");
        Scanner scanner = new Scanner(System.in);
        String searchWord = scanner.nextLine();
        for (Word word: allWords) {
            if (word.wordTarget.startsWith(searchWord)) {
                word.writeWord();
            }
        }
    }

    public void dictionaryBasic() {
        insertFromCommandline();
        showAllWords();
    }

    public void dictionaryAdvanced() {
        insertFromFile();
        showAllWords();
        while(true) {
            Scanner scanner = new Scanner(System.in);
            String command = scanner.nextLine();
            switch (command) {
                case "remove" -> removeWordFromDictionary();
                case "edit" -> editWordInDictionary();
                case "add" -> addWordToDictionary();
                case "lookup" -> dictionaryLookup();
                case "show" -> showAllWords();
            }
        }
    }

    public static void main(String[] args) {
        DictionaryCommandline myDictionary = new DictionaryCommandline();
        myDictionary.dictionaryAdvanced();
    }
}
