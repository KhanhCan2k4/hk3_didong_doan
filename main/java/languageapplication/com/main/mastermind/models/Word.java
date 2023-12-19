package languageapplication.com.main.mastermind.models;

import java.util.ArrayList;

import languageapplication.com.main.mastermind.config.Constains;

public class Word {
    private int id;
    private String word;
    private String meaning;
    private ArrayList<String> meanings;
    private String furigana;
    private String romaji;
    private int level;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public ArrayList<String> getMeanings() {
        return meanings;
    }

    public String getMeaningsString() {
        String result = "";
        for (String meaning: meanings) {
            result += Constains.MEANING_CHAR_KEY + meaning +"\n";
        }

        return result;
    }

    public void setMeanings(String... meanings) {
        this.meanings.clear();
        for (String meaning : meanings) {
            this.meanings.add(meaning);
        }
    }

    public String getFurigana() {
        return furigana;
    }

    public void setFurigana(String furigana) {
        this.furigana = furigana;
    }

    public String getRomaji() {
        return romaji;
    }

    public void setRomaji(String romaji) {
        this.romaji = romaji;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Word(int id, String word, String furigana, String romaji, int level) {
        this.id = id;
        this.word = word;
        this.meanings = new ArrayList<>();
        this.furigana = furigana;
        this.romaji = romaji;
        this.level = level;
    }

    public Word() {
        this.id = 0;
        this.word = "";
        this.meanings = new ArrayList<>();
        this.furigana = "";
        this.romaji = "";
        this.level = 0;
    }

    public void setWord(Word word) {
        this.id = word.id;
        this.word = word.word;
        this.meaning = word.meaning;
        this.furigana = word.furigana;
        this.romaji = word.romaji;
        this.level = word.level;
    }

    public String toString() {
        return word + Constains.MEANING_CHAR_KEY + furigana + Constains.MEANING_CHAR_KEY + romaji;
    }
}
