import java.util.Scanner;

public class Word {
    private String wordTarget;  // New word
    private String wordExplain; // Explanation of word

    public Word(String wordTarget, String wordExplain) {
        this.wordTarget = wordTarget;
        this.wordExplain = wordExplain;
    }

    public Word() {

    }

    public String getWordTarget() {
        return wordTarget;
    }

    public String getWordExplain() {
        return wordExplain;
    }

    public void readWord(Scanner scanner) {
        String[] word = scanner.nextLine().split("\\t", 2);
        this.wordTarget = word[0];
        this.wordExplain = word[1];
    }

    public void writeWord() {
        System.out.println(wordTarget + " : " + wordExplain);
    }

}
