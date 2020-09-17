import java.util.Scanner;

public class DictionaryManagement extends Dictionary {

    public void insertFromCommandline() {
        Scanner scanner = new Scanner(System.in);
        int numberOfVocabulary = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < numberOfVocabulary; i++) {
            String englishWord = scanner.nextLine();
            String vietnameseWord = scanner.nextLine();
            this.allWords.add(new Word(englishWord, vietnameseWord));
        }
    }
}
