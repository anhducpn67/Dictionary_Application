import java.util.Scanner;

public class DictionaryCommandline extends DictionaryManagement {

    public void showAllWords() {
        System.out.printf("%-15s %-20s %-15s%n", "No", "English", "Vietnamese");
        for (int i = 0; i < allWords.size(); i++) {
            System.out.printf("%-15d %-20s %-15s%n", (i + 1), allWords.get(i).getWordTarget(), allWords.get(i).getWordExplain());
        }
    }

    public void dictionarySearcher() {
        System.out.println("Write the word you want to search: ");
        Scanner scanner = new Scanner(System.in);
        String searchWord = scanner.nextLine();
        for (Word word: allWords) {
            if (word.getWordTarget().startsWith(searchWord)) {
                word.writeWord();
            }
        }
    }

    public void showAllCommand() {
        System.out.println("remove  Remove a word from dictionary");
        System.out.println("edit    Edit explain of a word in dictionary");
        System.out.println("add     Add a new word to dictionary");
        System.out.println("lookup  Find explain of a word");
        System.out.println("show    Show all words in dictionary");
        System.out.println("search  Show all words start with ...");
        System.out.println("export  Export current dictionary to file");
        System.out.println("exit    Close dictionary application");
    }

    public void dictionaryBasic() {
        insertFromCommandline();
        showAllWords();
    }

    public void dictionaryAdvanced() {
        insertFromFile();
        showAllWords();
        boolean isRunning = true;
        while(isRunning) {
            Scanner scanner = new Scanner(System.in);
            String command = scanner.nextLine();
            switch (command) {
                case "remove":
                    removeWordFromDictionary();
                    break;
                case "edit":
                    editWordInDictionary();
                    break;
                case "add":
                    addWordToDictionary();
                    break;
                case "lookup":
                    dictionaryLookup();
                    break;
                case "show":
                    showAllWords();
                    break;
                case "search":
                    dictionarySearcher();
                    break;
                case "export":
                    dictionaryExportToFile();
                    break;
                case "help":
                    showAllCommand();
                    break;
                case "exit":
                    isRunning = false;
                    break;
            }
        }
    }
}
