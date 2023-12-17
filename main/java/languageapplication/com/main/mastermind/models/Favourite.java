package languageapplication.com.main.mastermind.models;

public class Favourite {
    private int id;
    private int wordId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWordId() {
        return wordId;
    }

    public void setWordId(int wordId) {
        this.wordId = wordId;
    }

    public Favourite(int id, int wordId) {
        this.id = id;
        this.wordId = wordId;
    }

    public Favourite() {
        this.id = -1;
        this.wordId = -1;
    }


}
