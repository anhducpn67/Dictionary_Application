import java.util.Scanner;

public class Word {
    public String wordTarget;  // New word
    public String wordExplain; // Explanation of word

    public Word(String wordTarget, String wordExplain) {
        this.wordTarget = wordTarget;
        this.wordExplain = wordExplain;
    }

    public Word() {

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
