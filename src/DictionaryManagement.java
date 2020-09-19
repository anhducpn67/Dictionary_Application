import java.util.Scanner;

public class DictionaryManagement extends Dictionary {

    final String dictionary_path = "./resource/dictionaries.txt";

    public void insertFromCommandline() {
        Scanner scanner = new Scanner(System.in);
        int numberOfVocabulary = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < numberOfVocabulary; i++) {
            String wordTarget = scanner.nextLine();
            String wordExplain = scanner.nextLine();
            Word newWord = new Word(wordTarget, wordExplain);
            this.allWords.add(newWord);
        }
    }

    public void insertFromFile() {
        Scanner scanner = Utils.readFile(dictionary_path);
        while (true) {
            assert scanner != null;
            if (!scanner.hasNextLine()) break;
            String[] line = scanner.nextLine().split("\\t", 2);
            Word newWord = new Word(line[0], line[1]);
            this.allWords.add(newWord);
        }
    }

    public void dictionaryLookup() {
        System.out.print("Write the word you want to lookup: ");
        Scanner scanner = new Scanner(System.in);
        String lookupWord = scanner.nextLine();
        for (Word word: allWords) {
            if (word.wordTarget.equals(lookupWord)) {
                System.out.println(word.wordTarget + " : " + word.wordExplain);
                return;
            }
        }
        System.out.println("Can't find word \"" + lookupWord + "\" in dictionary!");
    }
}
