public class Word {
    static int numberWords = 108854;
    public int id;
    public String word;
    public String html;

    public Word(String word, String html) {
        numberWords += 1;
        this.id = numberWords;
        this.word = word;
        this.html = html;
    }
}
