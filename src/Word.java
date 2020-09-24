import java.util.Scanner;

public class Word {
    private String wordTarget;  // New word
    private String wordExplain; // Explanation of word

    public Word(String wordTarget, String wordExplain) {
        this.setWordTarget(wordTarget);
        this.setWordExplain(wordExplain);
    }

    public Word() {

    }

    public void readWord(Scanner scanner) {
        String[] word = scanner.nextLine().split("\\t", 2);
        if (word.length != 2) {
            System.out.println("Wrong form!");
            return;
        }
        this.setWordTarget(word[0]);
        this.setWordExplain(word[1]);
    }

    public void writeWord() {
        System.out.println(getWordTarget() + " : " + getWordExplain());
    }

    public String getWordTarget() {
        return wordTarget;
    }

    public void setWordTarget(String wordTarget) {
        this.wordTarget = wordTarget;
    }

    public String getWordExplain() {
        return wordExplain;
    }

    public void setWordExplain(String wordExplain) {
        this.wordExplain = wordExplain;
    }
}
